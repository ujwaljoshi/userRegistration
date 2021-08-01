package com.codingTest.userRegistration.service;

import com.codingTest.userRegistration.exception.BadRequestException;
import com.codingTest.userRegistration.model.User;
import com.codingTest.userRegistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserRegistrationService implements ExclusionService{

    @Autowired
    private UserRepository userRepository;

    private List<User> exclusionUsers = Arrays.asList(
            new User("adaLovelace", "Analytical3ngineRulz", LocalDate.parse("1815-12-10"), 85385075),
            new User("alanTuring", "eniGmA123", LocalDate.parse("1912-06-23"), 123456789),
            new User("konradZuse", "zeD1", LocalDate.parse("1910-06-22"), 987654321)
    );

    public  List<User> getAllExclusionUsers(){
        return exclusionUsers;
    }

    public List<User> getAllRegisterUsers(){
        return userRepository.findAll();
    }

    public User registerUser(User user){
            if(UserValidation.userFieldValidation(user)) {
                if (!validate(user.getDateOfBirth(), user.getSsn())) {
                    return userRepository.save(user);
                }else{
                    throw new BadRequestException("user is in exclusion list");
                }
            }else{
                throw new BadRequestException("Failed validation - invalid input field");
            }
    }

    public User patchUser(User user,int ssn){
        User user1 = userRepository.findById(ssn).get();
        if(UserValidation.userFieldValidation(user)){
            if(!user1.getUsername().equals(user.getUsername())){
                user1.setUsername(user.getUsername());
            }
            if(!user1.getPassword().equals(user.getPassword())){
                user1.setPassword(user.getPassword());
            }
        }else{
            throw new BadRequestException("Failed validation - invalid input field");
        }
        return userRepository.save(user1);
    }

    public boolean validate(LocalDate dateOfBirth, Integer ssn){
        return exclusionUsers
                .stream()
                .anyMatch(eu -> eu.getDateOfBirth().equals(dateOfBirth) &&
                        eu.getSsn().equals(ssn));
    }
}

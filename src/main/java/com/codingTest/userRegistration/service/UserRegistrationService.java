package com.codingTest.userRegistration.service;

import com.codingTest.userRegistration.exception.BadRequestException;
import com.codingTest.userRegistration.model.User;
import com.codingTest.userRegistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * The UserRegistrationService class implements the business logic
 * checks performed-
 * 1. parameters are valid according to specification
 * 2. user hasn't been already registered
 * 3. user is not in the exclusion list
 *  @author Revanth Yelluri
 */

@Service
public class UserRegistrationService implements ExclusionService{

    @Autowired
    private UserRepository userRepository;

    //User Exclusion list
    private final List<User> exclusionUsers = Arrays.asList(
            new User(1, "adaLovelace", "Analytical3ngineRulz", "1815-12-10", "85385075"),
            new User(2, "alanTuring", "eniGmA123", "1912-06-23", "123456789"),
            new User(3, "konradZuse", "zeD1", "1910-06-22", "987654321")
    );

    //retrieve all the users present in exclusion users list
    public  List<User> getAllExclusionUsers(){
        return exclusionUsers;
    }

    //retrieve all the registered users present in database
    public List<User> getAllRegisterUsers(){
        return userRepository.findAll();
    }

    //register the user after all checks are performed
    public User registerUser(User user){
        userFieldValidate(user);
        if (validate(user.getDateOfBirth(), user.getSsn())) {
            if(userAlreadyExist(user)) {
                return userRepository.save(user);
            } else {
                throw new BadRequestException("User already exists");
            }
        }else {
            throw new BadRequestException("User is in exclusion list");
        }
    }

    //validates the user parameters if the parameters are invalid it will throw BadRequest exception
    public void userFieldValidate(User user){
        if(UserValidation.validateUsername(user.getUsername()) &&
                UserValidation.validatePassword(user.getPassword()) &&
                UserValidation.validateDateOfBirth(user.getDateOfBirth())) {
        }else{
            throw new BadRequestException("Failed validation - invalid username or password");
        }
    }

    /*public User patchUser(User user,int ssn){
        User user1 = userRepository.findById(ssn).get();
        if(UserValidation.validateUsername(user.getUsername()) &&
                UserValidation.validatePassword(user.getPassword())){
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
    }*/

    //validates if the user already exist in the database
    public boolean userAlreadyExist(User user){
        return userRepository.findAll()
                .stream()
                .noneMatch(u -> u.getSsn().equals(user.getSsn()));
    }

    //validates the user if it is part of an exclusion user list
    public boolean validate(String dateOfBirth, String ssn){
        return exclusionUsers
                .stream().allMatch(eu -> !eu.getDateOfBirth().equals(dateOfBirth) &&
                        !eu.getSsn().equals(ssn));
    }
}

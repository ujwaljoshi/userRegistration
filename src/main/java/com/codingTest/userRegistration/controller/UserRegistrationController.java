package com.codingTest.userRegistration.controller;

import com.codingTest.userRegistration.model.User;
import com.codingTest.userRegistration.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("/users/exclusion")
    public List<User> getExcludedUsers(){
        return userRegistrationService.getAllExclusionUsers();
    }

    @GetMapping("/users")
    public List<User> getRegisteredUsers(){
        return userRegistrationService.getAllRegisterUsers();
    }

//    @PostMapping("/users")
//    public ResponseEntity<User> registerUser(@RequestBody User user){
//        return new ResponseEntity<>(userRegistrationService.registerUser(user), HttpStatus.CREATED);
//    }

    @PutMapping("/users")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<>(userRegistrationService.registerUser(user), HttpStatus.CREATED);
    }

    @PatchMapping("/users/{ssn}")
    public ResponseEntity<User> patchUser(@RequestBody User user, @PathVariable int ssn){
        return new ResponseEntity<>(userRegistrationService.patchUser(user,ssn), HttpStatus.OK);
    }
}

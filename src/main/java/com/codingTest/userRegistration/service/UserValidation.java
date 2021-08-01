package com.codingTest.userRegistration.service;

import com.codingTest.userRegistration.model.User;

public class UserValidation {

    public static boolean userFieldValidation(User user){

        if (!user.getUsername().contains(" ") &&
                user.getPassword().length()>=4 &&
                !user.getPassword().equals(user.getPassword().toLowerCase()) &&
                !user.getPassword().equals(user.getPassword().toUpperCase()) &&
                user.getPassword().matches(".*\\d.*")){
            return true;
        }
        return false;
    }
}

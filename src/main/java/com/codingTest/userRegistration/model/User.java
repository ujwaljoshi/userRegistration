package com.codingTest.userRegistration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User is a simple POJO class contains variables and their getters and setters
 *  @author Revanth Yelluri
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String dateOfBirth;
    private String ssn;

    public User(){

    }

    public User(Integer id, String username, String password, String dateOfBirth, String ssn){
        this.id = id;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return  password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public String getSsn(){
        return ssn;
    }

    public void setSsn(String ssn){
        this.ssn = ssn;
    }
}

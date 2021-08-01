package com.codingTest.userRegistration.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class User {

    private String username;
    private String password;
    private LocalDate dateOfBirth;
    @Id
    private Integer ssn;

    public User(){

    }

    public User(String username, String password, LocalDate dateOfBirth, int ssn){
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
    }

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getSsn(){
        return ssn;
    }

    public void setSsn(Integer ssn){
        this.ssn = ssn;
    }
}

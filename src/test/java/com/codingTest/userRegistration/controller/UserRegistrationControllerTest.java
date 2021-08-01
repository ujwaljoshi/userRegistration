package com.codingTest.userRegistration.controller;

import com.codingTest.userRegistration.exception.BadRequestException;
import com.codingTest.userRegistration.model.User;
import com.codingTest.userRegistration.service.UserRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRegistrationController.class)
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRegistrationController userRegistrationController;

    @MockBean
    private UserRegistrationService userRegistrationService;

    @Test
    public void registerUserTest() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("JohnJonny");
        user.setPassword("Helloworld1");
        user.setDateOfBirth(LocalDate.parse("1992-04-28"));
        user.setSsn(123456789);

        when(userRegistrationService.registerUser(any(User.class))).thenReturn(user);

        //mock request "/users"
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .content(new ObjectMapper().registerModule(new JavaTimeModule())
                        .writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("JohnJonny"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("Helloworld1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth").value("1992-04-28"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ssn").value("123456789"));

    }

    @Test
    public void registerUserWithInvalidFieldsTest() throws Exception {

        //mock the user data that we have to save
        User user = new User();
        user.setUsername("JohnJonny");
        user.setPassword("Helloworld1");
        user.setDateOfBirth(LocalDate.parse("1992-04-28"));
        user.setSsn(123456789);

        when(userRegistrationService.registerUser(any(User.class))).thenThrow(new BadRequestException());

        //mock request "/users"
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(new ObjectMapper().registerModule(new JavaTimeModule())
                                .writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
                /*.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("JohnJonny"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("Helloworld1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth").value("1992-04-28"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ssn").value("123456789"));*/

    }
}

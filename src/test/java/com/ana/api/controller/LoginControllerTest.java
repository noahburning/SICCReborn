package com.ana.api.controller;

import com.ana.api.entity.User;
import com.ana.api.repository.UserRepository;
import com.ana.api.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@AutoConfigureMockMvc
@SpringJUnitConfig
@SpringBootTest

public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoginController loginController;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLoginControllerValid() throws Exception {
        // test valid credentials

        // a valid username matches this expression "^[a-zA-Z0-9_-]{6,30}$"
        // between 6 and 30 characters and contains only letters, numbers, underscores, and hyphens
        String username = "valid_username";

        // a valid password matches this expression "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,30}$"
        // between 8 and 30 characters and contains at least one uppercase letter, one lowercase letter, and one digit
        String password = "ValidPassword1";

        // save this user to the database using the UserRepository
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName("John");
        user.setMiddleInitial("A");
        user.setLastName("Doe");
        user.setManager(false);
        user.setManagerId(null);

        // delete this user from the database if it already exists
        Optional<User> findUser = userRepository.findByUsername(username);
        findUser.ifPresent(user1 -> userRepository.delete(user1));

        // save this user to the database
        userRepository.save(user);

        // create a LoginRequest object and convert it to JSON
        // then pass it to the login endpoint using the mockMvc content method
        LoginRequest request = new LoginRequest(username, password);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);

        // perform the request
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // delete this user from the database
        findUser = userRepository.findByUsername(username);
        findUser.ifPresent(user1 -> userRepository.delete(user1));
    }

    @Test
    public void testLoginControllerInvalid() throws Exception {
        // test invalid credentials
        String username = "nonexistent_username";
        String password = "invalid@password";

        // create a LoginRequest object and convert it to JSON
        // then pass it to the login endpoint using the mockMvc content method
        LoginRequest request = new LoginRequest(username, password);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(request);

        // perform the request
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}

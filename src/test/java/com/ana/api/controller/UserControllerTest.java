package com.ana.api.controller;

import com.ana.api.entity.User;
import com.ana.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    private final static String USERNAME = "crtoro13";
    private final static String PASSWORD = "ValidPassword1";
    private final static String FIRST_NAME = "Cristiano";
    private final static String MIDDLE_INITIAL = "R";
    private final static String LAST_NAME = "Ronaldo";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUserByIdMapping() throws Exception
    {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setMiddleInitial(MIDDLE_INITIAL);
        user.setLastName(LAST_NAME);

        userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users").param("id", String.valueOf(user.getId())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(USERNAME));

        userRepository.delete(user);
    }

    @Test
    public void testGetUserByUsernameMapping() throws Exception
    {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setMiddleInitial(MIDDLE_INITIAL);
        user.setLastName(LAST_NAME);

        userRepository.save(user);

        String url = String.format("/users/%s", user.getUsername());
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(USERNAME));

        userRepository.delete(user);
    }

}

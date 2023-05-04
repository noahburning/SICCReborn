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

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUserByIdMapping() throws Exception
    {
        User user = new User();
        user.setUsername("crtoro13");
        user.setPassword("ValidPassword1");
        user.setFirstName("Cristiano");
        user.setMiddleInitial("R");
        user.setLastName("Ronaldo");

        userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users").param("id", String.valueOf(user.getId())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("cr7"));

        userRepository.delete(user);
    }

    @Test
    public void testGetUserByUsernameMapping() throws Exception
    {
        User user = new User();
        user.setUsername("cr7");
        user.setPassword("ValidPassword1");
        user.setFirstName("Cristiano");
        user.setMiddleInitial("R");
        user.setLastName("Ronaldo");

        userRepository.save(user);

        String url = String.format("/users/%s", user.getUsername());
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("cr7"));

        userRepository.delete(user);
    }

}

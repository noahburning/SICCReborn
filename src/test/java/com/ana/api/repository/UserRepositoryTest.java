package com.ana.api.repository;

import com.ana.api.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserById() throws Exception {
        User user = new User();
        user.setUsername("crtoro13"); // must be unique and longer than 5 characters (6-30)
        user.setPassword("ValidPassword1");
        user.setFirstName("Cristiano");
        user.setMiddleInitial("R");
        user.setLastName("Ronaldo");

        userRepository.save(user);

        User findUser = userRepository.findById(user.getId()).orElse(null);

        assertNotNull(findUser);
        assertEquals("crtoro13", findUser.getUsername());

        userRepository.delete(user);
    }

    @Test
    public void testFindByUsername() throws Exception {
        User user = new User();
        user.setUsername("crtoro13");
        user.setPassword("ValidPassword1");
        user.setFirstName("Cristiano");
        user.setMiddleInitial("R");
        user.setLastName("Ronaldo");

        userRepository.save(user);

        User findUser = userRepository.findByUsername("crtoro13").orElse(null);

        assertNotNull(findUser);
        assertEquals("crtoro13", findUser.getUsername());

        userRepository.delete(user);
    }

}

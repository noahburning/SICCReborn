package com.ana.api.repository;

import com.ana.api.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserRepositoryTest {

    private final static String USERNAME = "crtoro13";
    private final static String PASSWORD = "ValidPassword1";
    private final static String FIRST_NAME = "Cristiano";
    private final static String MIDDLE_INITIAL = "R";
    private final static String LAST_NAME = "Ronaldo";

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserById() throws Exception {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setMiddleInitial(MIDDLE_INITIAL);
        user.setLastName(LAST_NAME);

        userRepository.save(user);

        User findUser = userRepository.findById(user.getId()).orElse(null);

        assertNotNull(findUser);
        assertEquals(USERNAME, findUser.getUsername());

        userRepository.delete(user);
    }

    @Test
    public void testFindByUsername() throws Exception {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setMiddleInitial(MIDDLE_INITIAL);
        user.setLastName(LAST_NAME);

        userRepository.save(user);

        User findUser = userRepository.findByUsername(USERNAME).orElse(null);

        assertNotNull(findUser);
        assertEquals(USERNAME, findUser.getUsername());

        userRepository.delete(user);
    }

}

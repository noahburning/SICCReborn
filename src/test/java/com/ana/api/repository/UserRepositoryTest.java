package com.ana.api.repository;

import com.ana.api.entity.User;
import com.ana.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserById() throws Exception {
        User user = new User();
        user.setUsername("cr7");
        user.setFirstName("Cristiano");
        user.setMiddleInitial("R");
        user.setLastName("Ronaldo");

        userRepository.save(user);

        User findUser = userRepository.findById(user.getId()).orElse(null);

        assertNotNull(findUser);
        assertEquals("cr7", findUser.getUsername());

        userRepository.delete(user);
    }

    @Test
    public void testFindByUsername() throws Exception {
        User user = new User();
        user.setUsername("cr7");
        user.setFirstName("Cristiano");
        user.setMiddleInitial("R");
        user.setLastName("Ronaldo");

        userRepository.save(user);

        User findUser = userRepository.findByUsername("cr7");

        assertNotNull(findUser);
        assertEquals("cr7", findUser.getUsername());

        userRepository.delete(user);
    }

}

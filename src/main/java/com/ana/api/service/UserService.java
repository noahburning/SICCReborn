package com.ana.api.service;

import com.ana.api.entity.User;
import com.ana.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

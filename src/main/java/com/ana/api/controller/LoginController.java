package com.ana.api.controller;

import com.ana.api.entity.User;
import com.ana.api.request.LoginRequest;
import com.ana.api.service.UserService;
import com.ana.client.utility.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    // login controller post mapping to "/login" with username and password in the HTTP request body
    // if the username and password are valid, return the user object and a 200 status code
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Optional<User> user = userService.getByUsername(request.getUsername());

        final String REQ_MSG = String.format("Login request received. Username: {%s}, Password: {%s}", request.getUsername(), request.getPassword());
        logger.info(REQ_MSG);

        if(user.isEmpty()) {
            final String MSG = String.format("Did not find that user! Username: {%s}", request.getUsername());
            logger.error(MSG);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
        }

        if(!userService.matchPassword(user.get(), request.getPassword()))
        {
            final String MSG = String.format("Username: {%s}'s password: {%s} does not match the provided password: {%s}", request.getUsername(), user.get().getPassword(), request.getPassword());
            logger.error(MSG);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        UserContext.setIsManager(user.get().isManager());
        return ResponseEntity.ok("Login Successful!");
    }

}

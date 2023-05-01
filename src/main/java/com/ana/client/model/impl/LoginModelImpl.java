package com.ana.client.model.impl;

import com.ana.api.request.LoginRequest;
import com.ana.client.model.LoginModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class LoginModelImpl implements LoginModel {

    private static final Logger logger = LoggerFactory.getLogger(LoginModelImpl.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public LoginModelImpl(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * Validates that username is between 6 and 30 characters and contains only letters, numbers, underscores, and hyphens
     * and that password is between 8 and 30 characters and contains at least one uppercase letter, one lowercase letter, and one digit.
     *
     * @param username Username to validate
     * @param password Password to validate
     * @return True if username and password are valid, false otherwise
     */
    @Override
    public boolean validateCredentials(String username, String password) {
        // Check if username is between 6 and 30 characters and contains only letters, numbers, underscores, and hyphens
        String usernameRegex = "^[a-zA-Z0-9_-]{6,30}$";

        if (!username.matches(usernameRegex)) {
            logger.error("Username is invalid!");
            return false;
        }

        // Check if password is between 8 and 30 characters and contains at least one uppercase letter, one lowercase letter, and one digit
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$";

        if (!password.matches(passwordRegex)) {
            logger.error("Password is invalid!");
        }

        return password.matches(passwordRegex);
    }

    @Override
    public boolean login(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);

        logger.debug("Sending login request to {}", baseUrl);
        logger.debug("Username: {}", username);
        logger.debug("Password: {}", password);
        logger.debug("Hashed password: {}", hash_pass(password));

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
        return (response.getStatusCode() == HttpStatus.OK);
    }

    private String hash_pass(String password) {
        // Set up hashing parameters
        final int BUF = 16;
        final int ITERATION_COUNT = 65536;
        final int KEY_LENGTH = 128;
        final String ALGORITHM = "PBKDF2WithHmacSHA256";

        // Generate salt and hash password
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[BUF];
        random.nextBytes(salt);

        // KeySpec
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);

        // Hash password
        SecretKeyFactory factory;
        byte[] hash;

        try {
            factory = SecretKeyFactory.getInstance(ALGORITHM);
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Return hashed password encoded in Base64
        return Base64.getEncoder().encodeToString(hash);
    }

}

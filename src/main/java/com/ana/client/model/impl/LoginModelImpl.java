package com.ana.client.model.impl;

import com.ana.client.model.LoginModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LoginModelImpl implements LoginModel {

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
            return false;
        }

        // Check if password is between 8 and 30 characters and contains at least one uppercase letter, one lowercase letter, and one digit
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,30}$";
        return password.matches(passwordRegex);
    }

    @Override
    public boolean login(String username, String password) {
        // TODO:finish login functionality
        String url = baseUrl + "/login";
        ResponseEntity<?> response = restTemplate.postForEntity(url, null, null);

        return response.getStatusCode().is2xxSuccessful();
    }

}

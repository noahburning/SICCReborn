package com.ana.client.model;

public interface LoginModel {

    /**
     * Validates the provided username and password.
     * @param username the username to be validated
     * @param password the password to be validated
     * @return true if the credentials are valid, false otherwise
     */
    boolean validateCredentials(String username, String password);

    /**
     * Attempts to log in with the provided username and password.
     * @param username the username to log in with
     * @param password the password to log in with
     * @return true if the login was successful, false otherwise
     */
    boolean login(String username, String password);

}

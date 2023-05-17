package com.ana.client.view.impl;

import com.ana.api.entity.User;
import com.ana.client.listener.LoginListener;
import com.ana.client.view.LoginView;

import com.ana.client.utility.UserContext;

import javax.swing.*;
import java.awt.*;

public class LoginViewImpl extends JPanel implements LoginView {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private static final int TEXT_COLUMN_LENGTH = 30;
    private static final int GRID_ROWS = 2;
    private static final int GRID_COLUMNS = 2;
    private static final String LOGIN_LABEL = "Login";
    private static final String USERNAME_LABEL = "Username:";
    private static final String PASSWORD_LABEL = "Password:";

    public LoginViewImpl() {
        initComponents();
    }

    private void initComponents() {
        usernameField = new JTextField(TEXT_COLUMN_LENGTH);
        passwordField = new JPasswordField(TEXT_COLUMN_LENGTH);
        loginButton = new JButton(LOGIN_LABEL);

        JLabel usernameLabel = new JLabel(USERNAME_LABEL);
        JLabel passwordLabel = new JLabel(PASSWORD_LABEL);

        JPanel inputPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLUMNS));
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        add(inputPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
    }

    @Override
    public String getUsername() {
        String localUser = usernameField.getText();
        //locally saves the username for other purposes within the code
        UserContext.setUsername(localUser);

        return localUser;
    }

    @Override
    public String getPassword() {
        char[] password_array = passwordField.getPassword();
        StringBuilder password = new StringBuilder();

        for (char c : password_array) {
            password.append(c);
        }

        return password.toString();
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void clearInputs() {
        usernameField.setText("");
        passwordField.setText("");
    }

    @Override
    public void addLoginListener(LoginListener listener) {
        loginButton.addActionListener(e -> {
            listener.onLogin(getUsername(), getPassword());
            clearInputs();
        });
    }

}

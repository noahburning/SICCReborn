package com.ana.client.view.impl;

import com.ana.client.listener.LoginListener;
import com.ana.client.view.LoginView;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LoginViewImpl extends JPanel implements LoginView {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;

    public LoginViewImpl() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        add(inputPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
    }

    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public String getPassword() {
        return Arrays.toString(passwordField.getPassword());
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

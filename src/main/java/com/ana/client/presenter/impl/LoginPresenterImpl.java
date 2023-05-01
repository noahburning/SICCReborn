package com.ana.client.presenter.impl;

import com.ana.client.listener.LoginListener;
import com.ana.client.model.LoginModel;
import com.ana.client.presenter.LoginPresenter;
import com.ana.client.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginListener {

    private final LoginModel loginModel;
    private final LoginView loginView;

    public LoginPresenterImpl(LoginModel loginModel, LoginView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        this.loginView.addLoginListener(this);
    }

    @Override
    public void onLogin(String username, String password) {
        login();
    }

    @Override
    public void login() {
        final String INVALID_CRED_MSG = """
                Invalid credentials!
                Username must be between 6 and 30 characters and contain only letters, numbers, underscores, and hyphens.
                Password must be between 8 and 30 characters and contain at least one uppercase letter, one lowercase letter, and one digit.""";

        final String usernameInput = loginView.getUsername().trim();
        final String passwordInput = loginView.getPassword().trim();

        if (!loginModel.validateCredentials(usernameInput, passwordInput)) {
            loginView.showErrorMessage(INVALID_CRED_MSG);
            loginView.clearInputs();
        } else if (!loginModel.login(usernameInput, passwordInput)) {
            loginView.showErrorMessage("Login failed!");
        } else {
            loginView.showSuccessMessage("Login successful!");
            loginView.clearInputs();
        }
    }

}

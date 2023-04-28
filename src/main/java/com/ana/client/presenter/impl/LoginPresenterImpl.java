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
        String usernameInput = loginView.getUsername();
        String passwordInput = loginView.getPassword();


        if(!loginModel.validateCredentials(usernameInput, passwordInput)) {
            loginView.showErrorMessage("Invalid credentials!");
            loginView.clearInputs();
            return;
        }

        if(!loginModel.login(usernameInput, passwordInput)) {
            loginView.showErrorMessage("Login failed!");
        } else {
            loginView.showSuccessMessage("Login successful!");
            loginView.clearInputs();
        }
    }

}

package com.ana.client.presenter.impl;

import com.ana.client.gui.Navigator;
import com.ana.client.gui.ViewType;
import com.ana.client.listener.LoginListener;
import com.ana.client.model.LoginModel;
import com.ana.client.presenter.LoginPresenter;
import com.ana.client.utility.UserContext;
import com.ana.client.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginListener {

    private final LoginModel loginModel;
    private final LoginView loginView;
    private final Navigator navigator;

    public LoginPresenterImpl(LoginModel loginModel, LoginView loginView, Navigator navigator) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        this.navigator = navigator;
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
                If you have forgotten your credentials, please contact your system administrator.
                Username must be between 6 and 30 characters and contain only letters, numbers, underscores, and hyphens.
                Password must be between 8 and 30 characters and contain at least one uppercase letter, one lowercase letter, and one digit.""";

        final String LOGIN_FAILED_MSG = "Login failed!";

        final String usernameInput = loginView.getUsername().trim();
        final String passwordInput = loginView.getPassword().trim();

        boolean validCredentials = loginModel.validateCredentials(usernameInput, passwordInput);

        if (!validCredentials) {
            loginView.showErrorMessage(INVALID_CRED_MSG);
            return;
        }

        boolean loginSuccessful = loginModel.login(usernameInput, passwordInput);

        if (loginSuccessful) {
            if (UserContext.getIsManager()) {
                navigator.showView(ViewType.MANAGER_DASHBOARD.toString());
            } else {
                navigator.showView(ViewType.EMPLOYEE_DASHBOARD.toString());
            }
        } else {
            loginView.showErrorMessage(LOGIN_FAILED_MSG);
        }
    }

}

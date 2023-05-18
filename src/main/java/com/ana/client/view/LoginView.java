package com.ana.client.view;

import com.ana.client.listener.LoginListener;

public interface LoginView {

    String getUsername();

    String getPassword();

    void showErrorMessage(String message);

    void clearInputs();

    void addLoginListener(LoginListener listener);

}

package com.ana.client.gui;

import com.ana.client.model.LoginModel;
import com.ana.client.model.impl.LoginModelImpl;
import com.ana.client.presenter.impl.LoginPresenterImpl;
import com.ana.client.view.LoginView;
import com.ana.client.view.impl.LoginViewImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A class that creates the main window of the client.
 * The window is used to display the views.
 * The window also handles the exit of the client.
 * {@code @copyright}  Copyright (c) 2023
 *
 * @author Ali Ahmed
 */
public class ClientFrame extends JFrame {

    private static final String WINDOW_TITLE = "Super Innovative Cookie Code";
    private static final String BASE_URL = "http://localhost:8080";
    private static final String EXIT_ENDPOINT = BASE_URL + "/exit";
    protected final Navigator navigator;
    private final RestTemplate restTemplate;

    public ClientFrame() {
        this.navigator = new Navigator(this);
        this.restTemplate = new RestTemplate();
        clientExitHandler();
        init();
        addLoginView();
    }

    private void addLoginView() {
        LoginView loginView = new LoginViewImpl();
        LoginModel loginModel = new LoginModelImpl(restTemplate, BASE_URL.concat("/login"));
        new LoginPresenterImpl(loginModel, loginView);

        navigator.addView(ViewType.LOGIN.toString(), (JPanel) loginView);
        navigator.showView(ViewType.LOGIN.toString());
    }

    private void init() {
        super.setTitle(WINDOW_TITLE);
        super.setResizable(true);
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
        super.setMinimumSize(new Dimension(800, 600));
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    private void clientExitHandler() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String requestBody = "Server exit message";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_PLAIN);

                HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

                try {
                    restTemplate.postForObject(EXIT_ENDPOINT, request, String.class);
                } catch (Exception ex) {
                    System.out.println("The server isn't running.");
                }

                super.windowClosing(e);
            }
        });
    }

}

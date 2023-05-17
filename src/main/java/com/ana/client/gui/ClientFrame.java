package com.ana.client.gui;

import com.ana.api.service.ClockService;
import com.ana.api.service.UserService;
import com.ana.client.model.LoginModel;
import com.ana.client.model.impl.LoginModelImpl;
import com.ana.client.presenter.impl.AccessPOSPresenterImpl;
import com.ana.client.presenter.impl.ClockInPresenterImpl;
import com.ana.client.presenter.impl.EmployeeDashboardPresenterImpl;
import com.ana.client.presenter.impl.LoginPresenterImpl;
import com.ana.client.view.*;
import com.ana.client.view.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static ClientFrame instance = null;

    protected final Navigator navigator;
    private final RestTemplate restTemplate;

    @Autowired
    private UserService userService;
    @Autowired
    private ClockService clockService;

    private ClientFrame() {
        this.navigator = new Navigator(this);
        this.restTemplate = new RestTemplate();

        clientExitHandler();
        init();
        addLoginView();
        addEmployeeDashboardView();
        addManagerDashboardView();
        addClockInView();
        addPOSView();
    }

    public static ClientFrame getInstance() {
        if (instance == null) {
            instance = new ClientFrame();
        }
        return instance;
    }

    private void addLoginView() {
        LoginView loginView = new LoginViewImpl();
        LoginModel loginModel = new LoginModelImpl(restTemplate, BASE_URL.concat("/login"));
        new LoginPresenterImpl(loginModel, loginView, this.navigator);

        JPanel loginWrapper = new JPanel(new GridBagLayout());
        loginWrapper.add((JPanel) loginView);

        navigator.addView(ViewType.LOGIN.toString(), loginWrapper);
        //navigator.showView(ViewType.LOGIN.toString());
    }

    private void addEmployeeDashboardView() {
        EmployeeDashboardView employeeDashboardView = new EmployeeDashboardViewImpl();
        new EmployeeDashboardPresenterImpl(employeeDashboardView, this.navigator);

        JPanel employeeDashboardWrapper = new JPanel(new GridBagLayout());
        employeeDashboardWrapper.add((JPanel) employeeDashboardView);

        navigator.addView(ViewType.EMPLOYEE_DASHBOARD.toString(), employeeDashboardWrapper);
    }

    private void addManagerDashboardView() {
        ManagerDashboardView managerDashboardView = new ManagerDashboardViewImpl();

        JPanel managerDashboardWrapper = new JPanel(new GridBagLayout());
        managerDashboardWrapper.add((JPanel) managerDashboardView);

        navigator.addView(ViewType.MANAGER_DASHBOARD.toString(), managerDashboardWrapper);
        navigator.showView(ViewType.MANAGER_DASHBOARD.toString());
    }

    private void addClockInView() {
        ClockInView clockInView = new ClockInViewImpl();
        new ClockInPresenterImpl(clockInView, navigator, userService, clockService);


        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add((JPanel) clockInView);

        navigator.addView(ViewType.CLOCK.toString(), wrapper);
    }

    private void addPOSView() {
        AccessPOSView accessPOSView = new AccessPOSViewImpl();
        new AccessPOSPresenterImpl(accessPOSView, navigator);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add((JPanel) accessPOSView);

        navigator.addView(ViewType.SALES_TERMINAL.toString(), wrapper);
    }


    private void init() {
        super.setTitle(WINDOW_TITLE);
        super.setMinimumSize(new Dimension(800, 600));
        super.setResizable(true);
        super.pack();
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

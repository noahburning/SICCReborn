package com.ana.client.view.impl;

import com.ana.client.listener.LogoutListener;
import com.ana.client.view.EmployeeDashboardView;

import javax.swing.*;
import java.awt.*;

public class EmployeeDashboardViewImpl extends JPanel implements EmployeeDashboardView {

    private final JButton logoutButton;
    private final JLabel dashboardLabel;
    private LogoutListener logoutListener;  // Add this line

    public EmployeeDashboardViewImpl() {
        logoutButton = new JButton("Logout");
        dashboardLabel = new JLabel("Employee Dashboard", SwingConstants.CENTER);

        setLayout(new BorderLayout());
        add(logoutButton, BorderLayout.NORTH);
        add(dashboardLabel, BorderLayout.CENTER);

        // Add this line
        logoutButton.addActionListener(e -> {
            if (logoutListener != null) {
                logoutListener.onLogout();
            }
        });
    }

    @Override
    public void setLogoutListener(LogoutListener listener) {
        this.logoutListener = listener;
    }
}

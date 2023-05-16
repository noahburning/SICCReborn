package com.ana.client.view.impl;

import com.ana.client.listener.LogoutListener;
import com.ana.client.listener.ClockInListener;
import com.ana.client.view.EmployeeDashboardView;

import javax.swing.*;
import java.awt.*;

public class EmployeeDashboardViewImpl extends JPanel implements EmployeeDashboardView {

    private final JButton logoutButton;
    private final JButton clockInOutButton;
    private final JLabel dashboardLabel;
    private LogoutListener logoutListener;
    private ClockInListener clockInListener;

    public EmployeeDashboardViewImpl() {
        logoutButton = new JButton("Logout");
        clockInOutButton = new JButton("Clock In/Out");
        dashboardLabel = new JLabel("Employee Dashboard", SwingConstants.CENTER);

        setLayout(new BorderLayout());
        add(logoutButton, BorderLayout.NORTH);
        add(dashboardLabel, BorderLayout.CENTER);
        add(clockInOutButton, BorderLayout.SOUTH);  // Added clockInOutButton

        logoutButton.addActionListener(e -> {
            if (logoutListener != null) {
                logoutListener.onLogout();
            }
        });

        clockInOutButton.addActionListener(e -> {
            if (clockInListener != null) {
                clockInListener.onClockInOut();
            }
        });
    }

    @Override
    public void setLogoutListener(LogoutListener listener) {
        this.logoutListener = listener;
    }

    @Override
    public void setClockInListener(ClockInListener listener) {
        this.clockInListener = listener;
    }
}

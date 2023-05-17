package com.ana.client.view.impl;

import com.ana.client.listener.AccessPOSListener;
import com.ana.client.listener.LogoutListener;
import com.ana.client.listener.ClockInListener;
import com.ana.client.view.EmployeeDashboardView;

import javax.swing.*;
import java.awt.*;

public class EmployeeDashboardViewImpl extends JPanel implements EmployeeDashboardView {

    private final JButton logoutButton;
    private final JButton clockInOutButton;
    private final JLabel dashboardLabel;
    private final JButton accessPOSButton;
    private LogoutListener logoutListener;
    private ClockInListener clockInListener;
    private AccessPOSListener accessPOSListener;

    public EmployeeDashboardViewImpl() {
        logoutButton = new JButton("Logout");
        clockInOutButton = new JButton("Clock In/Out");
        dashboardLabel = new JLabel("Employee Dashboard", SwingConstants.CENTER);
        accessPOSButton = new JButton("Access POS");

        setLayout(new BorderLayout());
        add(logoutButton, BorderLayout.CENTER);
        add(dashboardLabel, BorderLayout.NORTH);
        add(clockInOutButton, BorderLayout.WEST);
        add(accessPOSButton, BorderLayout.EAST);

        Dimension buttonSize = logoutButton.getPreferredSize();
        buttonSize = new Dimension(buttonSize.width * 3, buttonSize.height * 2);
        logoutButton.setPreferredSize(buttonSize);
        clockInOutButton.setPreferredSize(buttonSize);
        accessPOSButton.setPreferredSize(buttonSize);

        Font buttonFont = logoutButton.getFont();
        buttonFont = buttonFont.deriveFont(buttonFont.getSize2D() * 2); // Increase the font size
        logoutButton.setFont(buttonFont);
        clockInOutButton.setFont(buttonFont);
        accessPOSButton.setFont(buttonFont);

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

        accessPOSButton.addActionListener(e -> {
            if (accessPOSListener != null) {
                accessPOSListener.onAccessPOS();
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

    @Override
    public void setAccessPOSListener(AccessPOSListener listener) {
        this.accessPOSListener = listener;
    }
}

package com.ana.client.view.impl;

import com.ana.client.listener.ClockInListener;
import com.ana.client.listener.ReturnListener;
import com.ana.client.view.ClockInView;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class ClockInViewImpl extends JPanel implements ClockInView {

    private JButton clockInButton;
    private JButton clockOutButton;
    private JButton returnButton; // New button
    private ClockInListener clockInListener;

    private ReturnListener returnListener;

    public ClockInViewImpl() {
        setLayout(new BorderLayout()); // Change layout to BorderLayout
        JLabel titleLabel = new JLabel("Don't forget to take breaks and wash your hands frequently.");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24)); // Set font size to 24
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 0)); // Set horizontal gap to 200
        clockInButton = new JButton("Clock In");
        clockOutButton = new JButton("Clock Out");
        returnButton = new JButton("Return"); // New button
        clockInButton.setFont(clockInButton.getFont().deriveFont(Font.BOLD, 16)); // Set font size to 16
        clockOutButton.setFont(clockOutButton.getFont().deriveFont(Font.BOLD, 16)); // Set font size to 16
        returnButton.setFont(returnButton.getFont().deriveFont(Font.BOLD, 16)); // Set font size to 16
        buttonPanel.add(clockInButton);
        buttonPanel.add(clockOutButton);
        buttonPanel.add(returnButton); // Add return button
        add(buttonPanel, BorderLayout.CENTER);

        clockInButton.addActionListener(e -> {
            if (clockInListener != null) {
                clockInListener.onClockIn();
            }
        });

        clockOutButton.addActionListener(e -> {
            if (clockInListener != null) {
                clockInListener.onClockOut();
            }
        });

        returnButton.addActionListener(e -> {
            if (returnListener != null) {
                returnListener.onReturn();
            }
        });
    }

    @Override
    public void setClockInListener(ClockInListener listener) {
        this.clockInListener = listener;
    }

    @Override
    public void showClockInMessage() {
        JOptionPane.showMessageDialog(this, "You have been clocked in.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showClockOutMessage() {
        JOptionPane.showMessageDialog(this, "You have been clocked out.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void addClockInListener(ClockInListener listener) {
        clockInButton.addActionListener(e -> listener.onClockIn());
        clockOutButton.addActionListener(e -> listener.onClockOut());
    }

    @Override
    public void setReturnListener(ReturnListener listener) {
        this.returnListener = listener;
    }
}

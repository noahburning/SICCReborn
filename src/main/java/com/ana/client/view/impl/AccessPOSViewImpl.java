package com.ana.client.view.impl;

import com.ana.client.listener.ReturnListener;
import com.ana.client.view.AccessPOSView;

import javax.swing.*;
import java.awt.*;

public class AccessPOSViewImpl extends JPanel implements AccessPOSView {

    private JButton returnButton;
    private ReturnListener returnListener;

    public AccessPOSViewImpl() {
        setPreferredSize(new Dimension(944, 569));
        setLayout(null);

        // Create a label
        JLabel posLabel = new JLabel("Point of Sales Terminal");
        posLabel.setFont(posLabel.getFont().deriveFont(Font.BOLD, 16));
        posLabel.setBounds(400, 0, 300, 20);
        add(posLabel);

        // Create a return button
        returnButton = new JButton("Return");
        returnButton.setFont(returnButton.getFont().deriveFont(Font.BOLD, 16));
        returnButton.setBounds(0, 0, 125, 35);
        add(returnButton);

        returnButton.addActionListener(e -> {
            if (returnListener != null) {
                returnListener.onReturn();
            }
        });
    }

    @Override
    public void setReturnListener(ReturnListener listener) {
        this.returnListener = listener;
    }
}

package com.ana.client.view.impl;

import com.ana.client.listener.ReturnListener;
import com.ana.client.view.AccessPOSView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessPOSViewImpl extends JPanel implements AccessPOSView {

    private final JButton returnButton;
    private final JButton calculateButton;
    private final JButton refundButton;
    private final JButton resetButton;
    private ReturnListener returnListener;
    private JLabel posLabel;
    private JLabel descriptionLabel;
    private JLabel[] itemLabels;
    private JLabel[] priceLabels;
    private JLabel totalLabel;
    private JLabel quantityLabel;
    private JTextField[] quantityFields;
    private JTextField totalTextField;

    public AccessPOSViewImpl() {
        setPreferredSize(new Dimension(944, 569));
        setLayout(null);

        // Create the main label
        posLabel = new JLabel("Point of Sales Terminal");
        posLabel.setFont(posLabel.getFont().deriveFont(Font.BOLD, 16));
        posLabel.setBounds(400, 0, 300, 20);
        add(posLabel);

        // Create the return button
        returnButton = new JButton("Return");
        returnButton.setFont(returnButton.getFont().deriveFont(Font.BOLD, 16));
        returnButton.setBounds(0, 0, 125, 35);
        add(returnButton);

        returnButton.addActionListener(e -> {
            if (returnListener != null) {
                returnListener.onReturn();
            }
        });

        // Create the item labels
        itemLabels = new JLabel[5];
        itemLabels[0] = new JLabel("Delicious classic chocolate chip");
        itemLabels[1] = new JLabel("Mmm regular sugar");
        itemLabels[2] = new JLabel("For those who really like it");
        itemLabels[3] = new JLabel("Better than you think");
        itemLabels[4] = new JLabel("A luxury classic");

        JLabel[] cookieLabels = new JLabel[5];
        cookieLabels[0] = new JLabel("Chocolate Chip");
        cookieLabels[1] = new JLabel("Sugar");
        cookieLabels[2] = new JLabel("Oatmeal");
        cookieLabels[3] = new JLabel("Macadamia Nut");
        cookieLabels[4] = new JLabel("Red Velvet");

        for (int i = 0; i < itemLabels.length; i++) {
            itemLabels[i].setBounds(400, 130 + i * 85, 195, 25);
            cookieLabels[i].setBounds(45, 130 + i * 85, 100, 25);
            add(itemLabels[i]);
            add(cookieLabels[i]);
        }

        // Create the price labels
        priceLabels = new JLabel[5];
        for (int i = 0; i < priceLabels.length; i++) {
            priceLabels[i] = new JLabel("$5.00");
            priceLabels[i].setBounds(625, 130 + i * 85, 100, 25);
            add(priceLabels[i]);
        }

        // Create the description label
        descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(400, 70, 100, 25);
        add(descriptionLabel);

        // Create the total label
        totalLabel = new JLabel("Total:");
        totalLabel.setBounds(710, 535, 100, 25);
        add(totalLabel);

        // Create the quantity labels and fields
        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(760, 70, 100, 25);
        add(quantityLabel);

        quantityFields = new JTextField[5];
        for (int i = 0; i < quantityFields.length; i++) {
            quantityFields[i] = new JTextField();
            quantityFields[i].setBounds(755, 130 + i * 85, 100, 25);
            add(quantityFields[i]);
        }

        // Create the calculate button
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(550, 535, 120, 25);
        add(calculateButton);

        // Create the refund button
        refundButton = new JButton("Refund");
        refundButton.setBounds(350, 535, 85, 25);
        add(refundButton);

        // Create the total text field
        totalTextField = new JTextField();
        totalTextField.setEditable(false);
        totalTextField.setBounds(760, 535, 100, 25);
        add(totalTextField);

        // Create the reset button
        resetButton = new JButton("Reset");
        resetButton.setBounds(250, 535, 85, 25);
        add(resetButton);

        // Add action listener to the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalQuantity = 0;
                for (JTextField field : quantityFields) {
                    String quantityText = field.getText();
                    if (!quantityText.isEmpty()) {
                        int quantity = Integer.parseInt(quantityText);
                        totalQuantity += quantity;
                    }
                }

                double totalPrice = totalQuantity * 5.0;
                totalTextField.setText(String.format("%.2f", totalPrice));
            }
        });

        // Add action listener to the refund button
        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalQuantity = 0;
                for (JTextField field : quantityFields) {
                    String quantityText = field.getText();
                    if (!quantityText.isEmpty()) {
                        int quantity = Integer.parseInt(quantityText);
                        totalQuantity += quantity;
                    }
                }

                double totalPrice = totalQuantity * -5.0;
                totalTextField.setText(String.format("%.2f", totalPrice));
            }
        });

        // Add action listener to the reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JTextField field : quantityFields) {
                    field.setText("");
                }
                totalTextField.setText("");
            }
        });
    }

    @Override
    public void setReturnListener(ReturnListener listener) {
        this.returnListener = listener;
    }
}

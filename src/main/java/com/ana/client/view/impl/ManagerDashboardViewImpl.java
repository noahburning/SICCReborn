package com.ana.client.view.impl;

import com.ana.client.listener.LogoutListener;
import com.ana.client.listener.LookupListener;
import com.ana.client.utility.EmployeeContext;
import com.ana.client.view.ManagerDashboardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class ManagerDashboardViewImpl extends JPanel implements ManagerDashboardView {

    private static final Logger logger = LoggerFactory.getLogger(ManagerDashboardViewImpl.class);

    private static final String FIRST_NAME_LABEL_TEXT = "First Name: [Placeholder]";
    private static final String MIDDLE_INITIAL_LABEL_TEXT = "Middle Initial: [Placeholder]";
    private static final String LAST_NAME_LABEL_TEXT = "Last Name: [Placeholder]";
    private static final String USERNAME_LABEL_TEXT = "Username: [Placeholder]";
    private static final String IS_MANAGER_LABEL_TEXT = "Manager: [Placeholder]";
    private static final String HOURS_WORKED_LABEL_TEXT = "Hours Worked: [Placeholder]";
    private static final String EMPLOYEE_ID_LABEL_TEXT = "Enter Employee ID:";
    private static final String LOGOUT_BUTTON_TEXT = "Logout";
    private static final String DASHBOARD_LABEL_TEXT = "Manage Employee by ID";
    private static final String EMPLOYEE_NAME_LABEL_TEXT = "Employee: [Placeholder]";
    private static final String SEARCH_BUTTON_TEXT = "Lookup";
    private static final String EMPLOYEE_NOT_FOUND_LABEL_TEXT = "Employee Not Found!";

    private JButton logoutButton;
    private JLabel dashboardLabel;
    private JLabel employeeNameLabel;
    private JLabel employeeFNameLabel;
    private JLabel employeeMInitLabel;
    private JLabel employeeLNameLabel;
    private JLabel usernameLabel;
    private JLabel isManagerLabel;
    private JLabel hoursWorkedLabel;
    private JLabel employeeIDLabel;
    private JTextField employeeIDTextField;
    private JButton searchButton;
    private JLabel foundEmployeeLabel;

    public ManagerDashboardViewImpl() {
        final int X_DIM = 950;
        final int Y_DIM = 750;

        setPreferredSize(new Dimension(X_DIM, Y_DIM));
        setLayout(null);

        initComponents();
        addComponents();
        setBounds();
    }

    private void initComponents() {
        // JButtons
        logoutButton = new JButton(LOGOUT_BUTTON_TEXT);
        searchButton = new JButton(SEARCH_BUTTON_TEXT);

        // JLabels
        dashboardLabel = new JLabel(DASHBOARD_LABEL_TEXT);
        employeeNameLabel = new JLabel(EMPLOYEE_NAME_LABEL_TEXT);
        employeeFNameLabel = new JLabel(FIRST_NAME_LABEL_TEXT);
        employeeMInitLabel = new JLabel(MIDDLE_INITIAL_LABEL_TEXT);
        employeeLNameLabel = new JLabel(LAST_NAME_LABEL_TEXT);
        usernameLabel = new JLabel(USERNAME_LABEL_TEXT);
        isManagerLabel = new JLabel(IS_MANAGER_LABEL_TEXT);
        hoursWorkedLabel = new JLabel(HOURS_WORKED_LABEL_TEXT);
        employeeIDLabel = new JLabel(EMPLOYEE_ID_LABEL_TEXT);
        foundEmployeeLabel = new JLabel(EMPLOYEE_NOT_FOUND_LABEL_TEXT);
        foundEmployeeLabel.setVisible(false);

        // JTextFields
        employeeIDTextField = new JTextField(10);

    }

    private void addComponents() {
        add(logoutButton);
        add(dashboardLabel);
        add(employeeNameLabel);
        add(employeeFNameLabel);
        add(employeeMInitLabel);
        add(employeeLNameLabel);
        add(usernameLabel);
        add(isManagerLabel);
        add(hoursWorkedLabel);
        add(employeeIDLabel);
        add(employeeIDTextField);
        add(searchButton);
        add(foundEmployeeLabel);
    }

    private void setBounds() {
        logoutButton.setBounds(50, 25, 110, 20);
        dashboardLabel.setBounds(300, 40, 150, 25);
        employeeNameLabel.setBounds(150, 70, 160, 25);
        employeeFNameLabel.setBounds(50, 105, 155, 25);
        employeeMInitLabel.setBounds(50, 135, 170, 25);
        employeeLNameLabel.setBounds(50, 165, 155, 25);
        usernameLabel.setBounds(50, 215, 150, 25);
        isManagerLabel.setBounds(50, 245, 145, 25);
        hoursWorkedLabel.setBounds(50, 275, 210, 25);
        employeeIDLabel.setBounds(50, 350, 125, 25);
        employeeIDTextField.setBounds(175, 350, 130, 25);
        searchButton.setBounds(315, 350, 100, 25);
        foundEmployeeLabel.setBounds(430, 350, 200, 25);
    }

    private String getEmployeeID() {
        return employeeIDTextField.getText().trim();
    }

    private void clearEmployeeID() {
        employeeIDTextField.setText("");
    }

    public void showEmployeeNotFound() {
        foundEmployeeLabel.setVisible(true);
    }

    @Override
    public void showEmployeeFound() {
        foundEmployeeLabel.setVisible(false);

        // also, show the employee info
        // get from Employee Context
        long id = EmployeeContext.getEmployeeId();
        String firstName = EmployeeContext.getFirstName();
        String middleInitial = EmployeeContext.getMiddleInitial();
        String lastName = EmployeeContext.getLastName();
        String username = EmployeeContext.getUsername();
        boolean isManager = EmployeeContext.getIsManager();

        employeeNameLabel.setText("Employee: " + firstName + " " + middleInitial + " " + lastName);
        employeeFNameLabel.setText("First Name: " + firstName);
        employeeMInitLabel.setText("Middle Initial: " + middleInitial);
        employeeLNameLabel.setText("Last Name: " + lastName);
        usernameLabel.setText("Username: " + username);
        isManagerLabel.setText("Is Manager: " + isManager);
        hoursWorkedLabel.setText("Hours Worked: " + 0);
    }

    @Override
    public void setLogoutListener(LogoutListener listener){
        logoutButton.addActionListener(e -> listener.onLogout());
    }

    @Override
    public void setLookupListener(LookupListener listener) {
        logger.info("Employee ID is {}", getEmployeeID());
        searchButton.addActionListener(e -> {
            listener.onLookup(getEmployeeID());
            clearEmployeeID();
        });
    }

}

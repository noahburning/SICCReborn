package com.ana.client.gui;

/**
 * Defines the different views that can be displayed in the client.
 * String values are used to identify the view in the {@link Navigator}.
 * <p>
 * {@code @copyright} Copyright (c) 2023
 *
 * @author Ali Ahmed
 */
public enum ViewType {

    LOGIN, EMPLOYEE_DASHBOARD, TIME_CARD, SALES_TERMINAL, MANAGER_DASHBOARD, CLOCK;

    private final String viewName;

    ViewType() {
        String viewName1 = "";

        switch (name()) {
            case "LOGIN" -> viewName1 = "login";
            case "EMPLOYEE_DASHBOARD" -> viewName1 = "employee dashboard";
            case "TIME_CARD" -> viewName1 = "time card";
            case "SALES_TERMINAL" -> viewName1 = "sales terminal";
            case "MANAGER_DASHBOARD" -> viewName1 = "manager dashboard";
            case "CLOCK" -> viewName1 = "clock"; // Added this line
        }

        this.viewName = viewName1;
    }

    @Override
    public String toString() {
        return viewName;
    }
}


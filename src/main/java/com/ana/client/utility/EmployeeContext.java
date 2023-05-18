package com.ana.client.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class EmployeeContext {

    private final static Logger logger = LoggerFactory.getLogger(UserContext.class);

    private static Long employeeId;

    private static String firstName;
    private static String middleInitial;
    private static String lastName;
    private static String username;
    private static boolean isManager;

    public static Long getEmployeeId() {
        return employeeId;
    }

    public static void setEmployeeId(Long employeeId) {
        logger.info("The employeeId entered and locally saved was {}", employeeId);
        EmployeeContext.employeeId = employeeId;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        logger.info("The firstName entered and locally saved was {}", firstName);
        EmployeeContext.firstName = firstName;
    }

    public static String getMiddleInitial() {
        return middleInitial;
    }

    public static void setMiddleInitial(String middleInitial) {
        logger.info("The middleInitial entered and locally saved was {}", middleInitial);
        EmployeeContext.middleInitial = middleInitial;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        logger.info("The lastName entered and locally saved was {}", lastName);
        EmployeeContext.lastName = lastName;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        logger.info("The username entered and locally saved was {}", username);
        EmployeeContext.username = username;
    }

    public static boolean getIsManager() {
        return isManager;
    }

    public static void setIsManager(boolean isManager) {
        logger.info("The isManager value entered and locally saved was {}", isManager);
        EmployeeContext.isManager = isManager;
    }

    public static void SaveEmployeeContext(Long employeeId, String firstName, String middleInitial, String lastName, String username, boolean isManager) {
        if(employeeId == 0L)
            return;

        setEmployeeId(employeeId);
        setFirstName(firstName);
        setMiddleInitial(middleInitial);
        setLastName(lastName);
        setUsername(username);
        setIsManager(isManager);
    }

}

package com.ana.client.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//locally stores the username data
public class UserContext {

    private final static Logger logger = LoggerFactory.getLogger(UserContext.class);

    private static String username;

    private static boolean isManager;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        logger.info("The username entered and locally saved was {}", username);
        UserContext.username = username;
    }

    public static boolean getIsManager() {
        return isManager;
    }

    public static void setIsManager(boolean isManager) {
        logger.info("The isManager value entered and locally saved was {}", isManager);
        UserContext.isManager = isManager;
    }

}

package com.ana.client.utility;
//locally stores the username data
public class UserContext {

    private static String username;

    private static boolean isManager;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        System.out.println("The username entered and locally saved was " + username);
        UserContext.username = username;
    }

    public static boolean getIsManager() {
        return isManager;
    }

    public static void setIsManager(boolean isManager) {
        UserContext.isManager = isManager;
    }

}

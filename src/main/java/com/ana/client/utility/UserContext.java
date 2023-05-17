package com.ana.client.utility;
//locally stores the username data
public class UserContext {

    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        System.out.println("The username entered and locally saved was " + username);
        UserContext.username = username;
    }
}

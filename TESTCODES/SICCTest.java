package SICCTest;

import java.sql.*;
import java.util.Scanner;

public class SICCTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://sicc.cb34ynl74nqz.us-east-2.rds.amazonaws.com:3306/mydatabase";
        String username = "master";
        String password = "password";

        // Prompt user for username and password
        Scanner scanner = new Scanner(System.in);
        boolean loginSuccessful = false;
        while (!loginSuccessful) {
            System.out.println("Enter username:");
            String inputUsername = scanner.nextLine();
            System.out.println("Enter password:");
            String inputPassword = scanner.nextLine();

            // Check if username and password are correct
            if (inputUsername.equals("noahrlopez") && inputPassword.equals("1234")) {
                System.out.println("Login successful!");
                loginSuccessful = true;
            } else {
                System.out.println("Error: Incorrect username or password. Please try again.");
            }
        }
    }
}

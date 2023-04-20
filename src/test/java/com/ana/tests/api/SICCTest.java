package com.ana.tests.api;
import java.sql.*;
import java.util.Scanner;

public class SICCTest {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://sicc.cb34ynl74nqz.us-east-2.rds.amazonaws.com:3306/siccANA";
        String username = "admin";
        String password = "password";
        String enteredUsername;
        String enteredPassword;

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            System.out.println("Connection established!");

            // Prompt user for username and password
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter username: ");
            enteredUsername = sc.nextLine();
            System.out.print("Enter password: ");
            enteredPassword = sc.nextLine();

            // Prepare and execute the SQL statement
            String sql = "SELECT * FROM User WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, enteredUsername);
            stmt.setString(2, enteredPassword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }

        } catch (SQLException e) {
            System.err.println("Error code: " + e.getErrorCode());
            e.printStackTrace();
        }
    }
}

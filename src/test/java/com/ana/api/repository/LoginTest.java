package com.ana.api.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class LoginTest {

    @Test
    @DisplayName("Test successful login")
    public void testSuccessfulLogin() {
        String dbUrl = "jdbc:mysql://siccbyana.ce2z68ofp2mx.us-east-1.rds.amazonaws.com:3306/cookieShopDB";
        String username = "admin";
        String password = "Dragonfly-Laurel-Serpent0-Purse-Scapegoat";
        String enteredUsername = "jsmith";
        String enteredPassword = "testpass123";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            System.out.println("Connection established!");

            // Prepare and execute the SQL statement
            String sql = "SELECT * FROM User WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, enteredUsername);
            stmt.setString(2, enteredPassword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
            } else {
                fail("Login failed");
            }

        } catch (SQLException e) {
            fail("SQL error occurred: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test invalid login")
    public void testInvalidLogin() {
        String dbUrl = "jdbc:mysql://siccbyana.ce2z68ofp2mx.us-east-1.rds.amazonaws.com:3306/cookieShopDB";
        String username = "admin";
        String password = "Dragonfly-Laurel-Serpent0-Purse-Scapegoat";
        String enteredUsername = "testuser";
        String enteredPassword = "wrongpassword";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            System.out.println("Connection established!");

            // Prepare and execute the SQL statement
            String sql = "SELECT * FROM User WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, enteredUsername);
            stmt.setString(2, enteredPassword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                fail("Login succeeded unexpectedly");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }

        } catch (SQLException e) {
            fail("SQL error occurred: " + e.getMessage());
        }
    }

}

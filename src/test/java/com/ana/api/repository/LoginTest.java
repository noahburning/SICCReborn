package com.ana.api.repository;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginTest {

    @Test
    @DisplayName("Test successful login")

    public void testSuccessfulLogin() {
        Connection conn = null;

        String enteredUsername = "admin1";
        String enteredPassword = "SiccAna2023";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://34.102.94.32:3306/cookieDB", "root", "1234");

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

    public void testInvalidLoginSQL() {

        Connection conn = null;

        String enteredUsername = "testuser";
        String enteredPassword = "wrongpassword";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://34.102.94.32:3306/cookieDB", "root", "1234");

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

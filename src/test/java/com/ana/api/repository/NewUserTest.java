package com.ana.api.repository;

import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.fail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes=com.ana.api.Application.class)

public class NewUserTest {

    @Test
    @DisplayName("Testing successful creation of new User")
    public void testNewUserCreation() {
        // Set up database connection

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://siccbyana.ce2z68ofp2mx.us-east-1.rds.amazonaws.com:3306/cookieShopDB", "admin", "Dragonfly-Laurel-Serpent0-Purse-Scapegoat");

            String fname = "test";
            String minit = "t";
            String lname = "test";
            String user = "testttest";
            String pass = "testtesttest";
            boolean is_manager = true;

            // Check if username already exists in User table
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM User WHERE username = '" + user + "'");
            if (rs.next() && rs.getInt(1) > 0) {
                fail("Error: username already exists.");
            }

            // Get new user ID by incrementing highest existing ID
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX(id) FROM User");
            int new_id = 1;
            if (rs.next()) {
                new_id = rs.getInt(1) + 1;
            }

            // Get new manager ID by incrementing highest existing manager ID
            int new_manager_id = 0;
            if (is_manager) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT MAX(mgr_id) FROM User");
                if (rs.next()) {
                    new_manager_id = rs.getInt(1) + 1;
                }
            }

            // Insert new user into User table
            pstmt = conn.prepareStatement("INSERT INTO User (id, fname, minit, lname, username, password, is_manager, mgr_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, new_id);
            pstmt.setString(2, fname);
            pstmt.setString(3, minit);
            pstmt.setString(4, lname);
            pstmt.setString(5, user);
            pstmt.setString(6, pass);
            pstmt.setBoolean(7, is_manager);
            pstmt.setInt(8, new_manager_id);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New user added with ID " + new_id + " and manager ID " + new_manager_id);
            } else {
                System.out.println("Error: user not added.");
            }

            // Delete user that was just added
            pstmt = conn.prepareStatement("DELETE FROM User WHERE id = ?");
            pstmt.setInt(1, new_id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User with ID " + new_id + " deleted successfully.");
            } else {
                System.out.println("Error: user with ID " + new_id + " not deleted.");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @DisplayName("Testing duplicate creation of new User")
    public void testDuplicateUserCreation() {
        // Set up database connection
        String dbUrl = "jdbc:mysql://siccbyana.ce2z68ofp2mx.us-east-1.rds.amazonaws.com:3306/cookieShopDB";
        String username = "admin";
        String password = "Dragonfly-Laurel-Serpent0-Purse-Scapegoat";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);

            String fname = "admin";
            String minit = "a";
            String lname = "admin";
            String user = "admin";
            String pass = "siccana2023";
            boolean is_manager = true;

            // Check if username already exists in User table
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM User WHERE username = '" + user + "'");
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("duplicate user, nothing new created");
            }
            else {
                fail("there is no duplicate");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

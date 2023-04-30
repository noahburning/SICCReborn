package com.ana.api.repository;

import org.junit.jupiter.api.*;
import java.sql.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class NewCookieTest {

    @Test
    public void testAddNewCookieAndDelete() throws SQLException {
        // create connection to the database
        Connection  conn = DriverManager.getConnection("jdbc:mysql://siccbyana.ce2z68ofp2mx.us-east-1.rds.amazonaws.com:3306/cookieShopDB", "admin", "Dragonfly-Laurel-Serpent0-Purse-Scapegoat");

        // find the previous id in the database
        int prevId = 0;
        PreparedStatement prevIdStmt = conn.prepareStatement("SELECT MAX(id) FROM Cookie");
        ResultSet prevIdRs = prevIdStmt.executeQuery();
        if (prevIdRs.next()) {
            prevId = prevIdRs.getInt(1);
        }

        // add a new cookie with the preset values
        String name = "Chocolate Chip";
        String description = "Savory Chocolate Chip cookie";
        BigDecimal price = new BigDecimal("4.99");
        PreparedStatement addCookieStmt = conn.prepareStatement("INSERT INTO Cookie (id, name, description, price) VALUES (?, ?, ?, ?)");
        addCookieStmt.setInt(1, prevId + 1);
        addCookieStmt.setString(2, name);
        addCookieStmt.setString(3, description);
        addCookieStmt.setBigDecimal(4, price);
        int rowsAffected = addCookieStmt.executeUpdate();

        // verify that the cookie was added successfully
        if (rowsAffected == 1) {
            System.out.println("Cookie " + (prevId + 1) + " successfully created");
        } else {
            fail("Cookie creation failed");
        }

        // delete the cookie
        PreparedStatement deleteCookieStmt = conn.prepareStatement("DELETE FROM Cookie WHERE id = ?");
        deleteCookieStmt.setInt(1, prevId + 1);
        int rowsDeleted = deleteCookieStmt.executeUpdate();

        // verify that the cookie was deleted successfully
        if (rowsDeleted == 1) {
            System.out.println("Cookie " + (prevId + 1) + " successfully deleted");
        } else {
            fail("Deletion failed");
        }

        // close database connection
        conn.close();
    }
}

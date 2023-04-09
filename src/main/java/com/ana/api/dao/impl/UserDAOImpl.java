package com.ana.api.dao.impl;

import com.ana.api.dao.UserDAO;
import com.ana.api.entity.User;

import java.sql.*;
import java.util.*;
public class UserDAOImpl implements UserDAO {

    private Connection con;

    public UserDAOImpl() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            String url = "jdbc:mysql://localhost:3306/user";
            String user = "root";
            String passwd = "passwd";
            con = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAllEmployees() {
        return null;
    }

    @Override
    public List<User> getAllManagers() {
        return null;
    }
}

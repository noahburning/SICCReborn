package com.ana.api.dao;

import com.ana.api.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    public User getUserById(int id);

    public void addUser(User user);

    public void updateUser(User user);

    public void delete(int id);

    public List<User> getAllEmployees();

    public List<User> getAllManagers();
}

package com.ana.api.dao.impl;

import com.ana.api.dao.IUserDAO;
import com.ana.api.entity.User;

import java.util.List;
public class UserDAO implements IUserDAO {

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public void updatePassword(User user, String newPassword) {

    }
}

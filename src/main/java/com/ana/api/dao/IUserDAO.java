package com.ana.api.dao;

import com.ana.api.entity.User;

/**
 * The interface for the User DAO.
 * The DAO is responsible for all database operations for the User entity.
 * It extends the base DAO interface and is implemented by the User DAO implementation class.
 * It is used by the User service.
 */
public interface IUserDAO extends IBaseDAO<User> {
   /**
    * Gets a user by its username.
    *
    * @param username The username of the user.
    * @return The user with the given username.
    */
    User getByUsername(String username);

    /**
     * Updates a user's password.
     * This method is used when a user changes their password.
     */
    void updatePassword(User user, String newPassword);
}

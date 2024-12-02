package it.giomrc.altrotest.dao;


import it.giomrc.altrotest.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;
    User getUserById(int id) throws SQLException;

    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);

}

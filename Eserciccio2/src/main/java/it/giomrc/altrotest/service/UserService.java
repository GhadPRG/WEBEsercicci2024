package it.giomrc.altrotest.service;


import it.giomrc.altrotest.dao.UserDAO;
import it.giomrc.altrotest.dao.UserDAOImpl;
import it.giomrc.altrotest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDAOImpl userDAO;

    public UserService(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers(){
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(int id){
        if(id<0){
            return null;
        }
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyUser(String user, String password){
        System.out.println("Verifying user"+user+" and password"+password);
        if(user==null || password==null){
            return false;
        }

        try {
            return userDAO.verifyUser(user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
package it.giomrc.altrotest.dao;

import it.giomrc.altrotest.model.User;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO{

    Connection connection;
    public UserDAOImpl() {
        this.connection=DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM utenti";


        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            users.add(user);
        }
        System.out.println(users.toString());
        return users;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM utenti WHERE id = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        return user;
    }

    public boolean verifyUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM utenti WHERE username = ? AND password=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            System.out.println("Verifico l'utente: "+username);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return true;
            }
        return false;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO utenti (username, password) VALUES (?, ?)";
        //Da finire
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE utenti SET username = ?, password = ? WHERE id = ?";
        //Da finire
    }

    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM utenti WHERE id = ?";
        //Da finire
    }
}

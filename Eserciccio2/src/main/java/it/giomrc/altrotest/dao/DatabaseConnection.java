package it.giomrc.altrotest.dao;

import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private PiattoDAO piattoDAO = null;
    private RistoranteDAO ristoranteDAO = null;

    private static DatabaseConnection instance;

    public DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }



    Connection con=null;
    public Connection getConnection(){
        if(con==null){
            String jdbcUrl = "jdbc:postgresql://localhost:5432/testWEB";
            String username = "postgres";
            String password = "password";
            System.out.println("Connessione...");
            try{
                con = DriverManager.getConnection(jdbcUrl, username, password);
                System.out.println("Nome db: "+con.getCatalog());
            }catch (SQLException e){
                throw new RuntimeException();
            }

        }
        return con;
    }


    public PiattoDAO getPiattoDAO(){
        if (piattoDAO == null) {
            piattoDAO = new PiattoDAO();
        }
        return  piattoDAO;
    }


}

package it.giomrc.altrotest.dao;

import it.giomrc.altrotest.model.Piatto;
import it.giomrc.altrotest.model.Ristorante;

import java.sql.*;
import java.util.*;

public class PiattoDAO implements DAO<Piatto> {

    Connection connection= DatabaseConnection.getInstance().getConnection();

    public PiattoDAO() {
    }

    @Override
    public Set<Piatto> findAll() throws Exception {
        String query = "SELECT * FROM Piatto";
        Set<Piatto> piatti= new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Piatto piatto = new Piatto();
                piatto.setId(rs.getLong("id"));
                piatto.setNome(rs.getString("nome"));
                piatti.add(piatto);
            }
        }

        System.out.println("Piatti: "+piatti);
        return piatti;
    }

    @Override
    public Piatto findById(Long id) throws Exception {

        String query = "SELECT * FROM Piatto WHERE id = ?";
        Piatto piatto = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    piatto = new Piatto();
                    piatto.setId(rs.getLong("id"));
                    piatto.setNome(rs.getString("nome"));
                }
            }
        }
        return piatto;
    }

    @Override
    public void create(Piatto piatto) throws Exception {

        String query = "INSERT INTO Piatto (nome) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, piatto.getNome());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    piatto.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public void update(Piatto piatto) throws Exception {
        String query = "UPDATE Piatto SET nome = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, piatto.getNome());
            statement.setLong(2, piatto.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        String query = "DELETE FROM Piatto WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Metodo per trovare i ristoranti che servono un piatto
    public Set<Ristorante> findRistorantiByPiatto(Long idPiatto) throws Exception {

        String query = "SELECT r.id, r.nome FROM Ristorante r " +
                "JOIN RistorantePiatto rp ON r.id = rp.id_ristorante " +
                "WHERE rp.id_piatto = ?";
        Set<Ristorante> ristoranti = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, idPiatto);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Ristorante ristorante = new Ristorante();
                    ristorante.setId(rs.getLong("id"));
                    ristorante.setNome(rs.getString("nome"));
                    ristoranti.add(ristorante);
                }
            }
        }
        return ristoranti;
    }



}
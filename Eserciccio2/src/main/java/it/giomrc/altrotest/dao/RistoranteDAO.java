package it.giomrc.altrotest.dao;

import it.giomrc.altrotest.model.Piatto;
import it.giomrc.altrotest.model.Ristorante;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.*;
import java.util.*;

@Service
public class RistoranteDAO implements DAO<Ristorante> {

    Connection connection;
    public RistoranteDAO() {
        this.connection=DatabaseConnection.getInstance().getConnection();
    }


    @Override
    public Set<Ristorante> findAll() {
        Set<Ristorante> ristoranti = new HashSet<>();
        String query = "select * from ristorante";

        System.out.println("going to execute:"+query);

        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Ristorante rist = new RistoranteProxy();
                rist.setNome(rs.getString("nome"));
                ristoranti.add(rist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ristoranti;
    }

    @Override
    public Ristorante findById(Long id) throws Exception {
        return null;
    }

    @Override
    public void create(Ristorante ristorante) throws Exception {

        
        String query = "INSERT INTO Ristorante (nome) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, ristorante.getNome());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    ristorante.setId(rs.getLong(1));
                }
            }
        }
    }
    @Override
    public void update(Ristorante ristorante) throws Exception {

        
        String query = "UPDATE Ristorante SET nome = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ristorante.getNome());
            statement.setLong(2, ristorante.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws Exception {

        
        String query = "DELETE FROM Ristorante WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Metodo per aggiungere un piatto a un ristorante
    public void addPiatto(Long idRistorante, Long idPiatto) throws Exception {

        
        String query = "INSERT INTO RistorantePiatto (id_ristorante, id_piatto) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, idRistorante);
            statement.setLong(2, idPiatto);
            statement.executeUpdate();
        }
    }

    // Metodo per recuperare i piatti di un ristorante, gestisco l'associazione molti a molti.
    public Set<Piatto> findPiattiByRistorante(Long idRistorante) throws Exception {

        
        String query = "SELECT p.id, p.nome FROM Piatto p " +
                "JOIN RistorantePiatto rp ON p.id = rp.id_piatto " +
                "WHERE rp.id_ristorante = ?";
        Set<Piatto> piatti = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, idRistorante);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Piatto piatto = new Piatto();
                    piatto.setId(rs.getLong("id"));
                    piatto.setNome(rs.getString("nome"));
                    piatti.add(piatto);
                }
            }
        }
        return piatti;
    }

    public void addPiattoToRistorante(Long idRistorante, Long idPiatto) throws SQLException {

        

        String query = "INSERT INTO RistorantePiatto (id_ristorante, id_piatto) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, idRistorante);
            statement.setLong(2, idPiatto);
            statement.executeUpdate();
        }
    }

    //Metodo per ottenere tutti i ristoranti e i piatti che fanno
    public Map<String, List<String>> getAllPlatesOfRestaurant(){
        System.out.println("getAllPlates test");
        List<Object[]> queryResults=new ArrayList<>();
        String query="SELECT r.nome AS ristorante_nome, p.nome AS piatto_nome " +
                "FROM ristorante r " +
                "JOIN ristorantepiatto rp ON r.id = rp.id_ristorante " +
                "JOIN piatto p ON rp.id_piatto = p.id";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Piatto piatto = new Piatto();
                Ristorante ristorante = new Ristorante();
                ristorante.setNome(rs.getString("ristorante_nome"));
                piatto.setNome(rs.getString("piatto_nome"));
                System.out.println("Nome rist:"+ristorante.getNome());
                Object[] a=new Object[]{ristorante.getNome(), piatto.getNome()};
                queryResults.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Map<String, List<String>> ristorantePiattiMap = new HashMap<>();
        for (Object[] row : queryResults) {
            String ristoranteNome = (String) row[0];
            String piattoNome = (String) row[1];

            // Aggiungi alla mappa
            ristorantePiattiMap.putIfAbsent(ristoranteNome, new ArrayList<>());
            ristorantePiattiMap.get(ristoranteNome).add(piattoNome);
        }

        ristorantePiattiMap.forEach((ristorante, piatti) -> {
            System.out.println("Ristorante: " + ristorante);
            for (String piattoNome : piatti) {
                System.out.println(piattoNome);
            }
        });

        return ristorantePiattiMap;
    }
}

package it.giomrc.altrotest.service;

import it.giomrc.altrotest.dao.PiattoDAO;
import it.giomrc.altrotest.dao.RistoranteDAO;
import it.giomrc.altrotest.model.Piatto;
import it.giomrc.altrotest.model.Ristorante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RistoranteService {

    private final RistoranteDAO ristoranteDAO;

    private final PiattoDAO piattoDAO;

    public RistoranteService(RistoranteDAO ristoranteDAO, PiattoDAO piattoDAO) {
        this.ristoranteDAO = ristoranteDAO;
        this.piattoDAO = piattoDAO;
    }

    public RistoranteService(){
        ristoranteDAO = new RistoranteDAO();
        piattoDAO=new PiattoDAO();
    }
    // Trova tutti i ristoranti
    public Set<Ristorante> findAllRistoranti() throws Exception {
        return ristoranteDAO.findAll();
    }

    // Trova un ristorante per ID
    public Ristorante findRistoranteById(Long id) throws Exception {
        Ristorante ristorante = ristoranteDAO.findById(id);
        if (ristorante == null) {
            throw new IllegalArgumentException("Ristorante non trovato con ID: " + id);
        }
        return ristorante;
    }

    // Aggiunge un piatto esistente a un ristorante
    public void addPiattoToRistorante(Long idRistorante, Long idPiatto) throws Exception {
        // Controlla che il ristorante esista
        Ristorante ristorante = ristoranteDAO.findById(idRistorante);
        if (ristorante == null) {
            throw new IllegalArgumentException("Ristorante non trovato con ID: " + idRistorante);
        }

        // Controlla che il piatto esista
        Piatto piatto = piattoDAO.findById(idPiatto);
        if (piatto == null) {
            throw new IllegalArgumentException("Piatto non trovato con ID: " + idPiatto);
        }

        // Aggiunge il piatto al ristorante
        ristoranteDAO.addPiattoToRistorante(idRistorante, idPiatto);
    }


    // Recupera tutti i piatti serviti da un ristorante
    public Set<Piatto> getPiattiByRistorante(Long idRistorante) throws Exception {
        return ristoranteDAO.findPiattiByRistorante(idRistorante);
    }

    public Map<String, List<String>> getAllPlatesOfRestaurant(){
        return ristoranteDAO.getAllPlatesOfRestaurant();
    }

}

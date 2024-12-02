package it.giomrc.altrotest.dao;

import it.giomrc.altrotest.model.Piatto;
import it.giomrc.altrotest.model.Ristorante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

//I dati presenti in questa classe vengono richiamati nel momento in cui c'è effettivo bisogno, applico quindi un approccio
//Lazy Loading.

@Service
public class RistoranteProxy extends Ristorante {

    public Set<Piatto> getPiatti() {
            try {
                this.piatti= DatabaseConnection.getInstance().getPiattoDAO().findAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return piatti;
    }

}

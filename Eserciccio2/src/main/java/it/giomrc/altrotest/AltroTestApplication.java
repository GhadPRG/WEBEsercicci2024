package it.giomrc.altrotest;

import it.giomrc.altrotest.dao.DatabaseConnection;
import it.giomrc.altrotest.dao.PiattoDAO;
import it.giomrc.altrotest.dao.RistoranteDAO;
import it.giomrc.altrotest.model.Piatto;
import it.giomrc.altrotest.model.Ristorante;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

@ServletComponentScan
@SpringBootApplication
public class AltroTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AltroTestApplication.class, args);
                /*
            try (Connection connection = DatabaseConnection.getConnection()) {


                RistoranteDAO ristoranteDAO = new RistoranteDAO(connection);
                Ristorante ristorante=ristoranteDAO.findById(1L);
                Set<Piatto> pia = ristorante.getPiatti(); //Recupera i piatti solo nel momento in cui vengono richiesti dal metodo .getPiatti
                System.out.println("Piatti ristorante A:");
                pia.forEach(p -> System.out.println(p.getNome()));
                //Gestisco la relazione molti a molti, ad esempio, aggiungendo al ristorante A (con id=1) il piatto Risotto, con id=3.
                //ristoranteDAO.addPiattoToRistorante(1L, 3L); //Ho aggiunto al ristorante A il piatto 3

                ristorante=ristoranteDAO.findById(1L);
                pia = ristorante.getPiatti();
                System.out.println("Piatti ristorante A dopo aver aggiunto piatto nuovo:");
                pia.forEach(p -> System.out.println(p.getNome()));

                //Allo stesso modo posso eseguire operazioni che dissociano un ristorante da un piatto
                //Oppure che prendono solo i ristoranti che hanno quel piatto
                //Ad esempio, voglio prendere i ristoranti che hanno i piatti con id=3
                PiattoDAO ristoranteDAO = new PiattoDAO(connection);
                Set<Ristorante> ristoranti = ristoranteDAO.findRistorantiByPiatto(3L);
                System.out.println("Stampa dei ristoranti con piatto id=3: ");
                ristoranti.forEach(r->System.out.println(r.getNome()));



            } catch (Exception e) {
                e.printStackTrace();
            }
            */




    }

}

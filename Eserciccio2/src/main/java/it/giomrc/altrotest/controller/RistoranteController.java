package it.giomrc.altrotest.controller;

import it.giomrc.altrotest.dao.RistoranteProxy;
import it.giomrc.altrotest.service.RistoranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Controller
@RequestMapping("/ristoranti")
public class RistoranteController {

    private final RistoranteService ristoranteService;

    private final RistoranteProxy ristoranteProxy;

    public RistoranteController() {
        this.ristoranteService = new RistoranteService();
        this.ristoranteProxy = new RistoranteProxy();
    }


    @GetMapping
    public String getAllRistoranti(Model model) {

        try {

            model.addAttribute("ristoranti", ristoranteService.findAllRistoranti());
            System.out.println("Recupero ora i piatti");
            model.addAttribute("piatti", ristoranteProxy.getPiatti());
            System.out.println("Piatti recuperati: Test stampa ristoranti:");
            Map<String, List<String>> rist=ristoranteService.getAllPlatesOfRestaurant();
            model.addAttribute("ristorantiPiatti", rist);

        } catch (Exception e) {
            model.addAttribute("error", "Errore nel recupero dei ristoranti");
        }
        return "ristoranti";
    }

    @PostMapping("/{id}/aggiungi-piatto")
    public String aggiungiPiattoARistorante(@PathVariable Long id, @RequestParam Long idPiatto) {
        try {
            ristoranteService.addPiattoToRistorante(id, idPiatto);
        } catch (Exception e) {
            return "redirect:/ristoranti?error=Errore nell'aggiunta del piatto";
        }
        return "redirect:/ristoranti";
    }
}
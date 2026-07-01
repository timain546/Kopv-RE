package com.cooperative.transport.controllers;

import com.cooperative.transport.entities.Voyages;
import com.cooperative.transport.entities.VoyageStatut;
import com.cooperative.transport.services.VoyageService;

import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class VoyageController {

    @Autowired
    private VoyageService service;

    @GetMapping("/voyage/list")
    public String getListeVoyages(Model model) {
        List<Voyages> voyages = service.findAllVoyages();
        int nbActif = 0;
        for(Voyages v : voyages) {
            VoyageStatut vs = v.getStatutActuel();
            if(vs.getStatut().getLibelle().equalsIgnoreCase("En cours")) nbActif++;
        }

        model.addAttribute("nbActif", new Integer(nbActif));
        model.addAttribute("listeVoyages", voyages);
        return "liste-voyages";
    }

    @GetMapping("/api/voyage/annuler/{id}")
    public ResponseEntity<Map<String, Object>> annulerVoyage(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<Voyages> optionalVoyage = service.findVoyageById(id);

            if(!optionalVoyage.isEmpty()) {
                Voyages voyage = optionalVoyage.get();
                String libelleStatut = voyage.getStatutActuel().getStatut().getLibelle();

                if(libelleStatut.equalsIgnoreCase("En cours") || libelleStatut.equalsIgnoreCase("Terminé") || libelleStatut.equalsIgnoreCase("Annulé")) {
                    response.put("status", "error");
                    response.put("message", "Impossible d'annuler un voyage déjà en cours ou terminé");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }

                // Faire appel à la méthode de service
                service.annuler(voyage);

                response.put("status", "success");
                response.put("message", "Le voyage V-00" + voyage.getId() + " a été annulé avec succès!!");
                return ResponseEntity.ok(response);

            } else {
                response.put("status", "error");
                response.put("message", "Voyage introuvable");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch(Exception e) {
            response.put("status", "error");
            response.put("message", "Une erreur interne est survenue : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
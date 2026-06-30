package com.cooperative.transport.controllers;

import com.cooperative.transport.entities.Voyages;
import com.cooperative.transport.entities.VoyageStatut;
import com.cooperative.transport.services.VoyageService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
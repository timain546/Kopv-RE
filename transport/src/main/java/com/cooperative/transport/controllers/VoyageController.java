package com.cooperative.transport.controllers;

import com.cooperative.transport.entities.Voyages;
import com.cooperative.transport.services.VoyageService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VoyageController {

    @Autowired
    private VoyageService service;

    @GetMapping("/voyage/list")
    public String getListeVoyages(Model model) {
        List<Voyages> voyages = service.findAllVoyages();

        model.addAttribute("listeVoyages", voyages);
        return "liste-voyages";
    }
}
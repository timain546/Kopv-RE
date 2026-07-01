package com.cooperative.transport.services;

import com.cooperative.transport.entities.Voyages;
import com.cooperative.transport.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    public List<Voyages> findAllVoyages() {
        return voyageRepository.findAllCatalogueVoyage();
    }

    public Optional<Voyages> findVoyageById(Integer id) {
        return voyageRepository.findById(id);
    }
}

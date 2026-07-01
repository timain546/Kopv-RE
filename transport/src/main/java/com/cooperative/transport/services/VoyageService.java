package com.cooperative.transport.services;

import com.cooperative.transport.entities.*;
import com.cooperative.transport.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private StatutVoyageRepository statutVoyageRepo;

    @Autowired
    private VoyageStatutRepository voyageStatutRepo;

    public List<Voyages> findAllVoyages() {
        return voyageRepository.findAllCatalogueVoyage();
    }

    public Optional<Voyages> findVoyageById(Integer id) {
        return voyageRepository.findById(id);
    }

    public void annuler(Voyages voyage) {
        VoyageStatut voyagestatut = new VoyageStatut();
        voyagestatut.setVoyage(voyage);
        Optional<StatutVoyage> statut = statutVoyageRepo.findByLibelle("Annulé");
        StatutVoyage statutAnnule = statut.get();
        voyagestatut.setStatut(statutAnnule);
        voyagestatut.setDateModification(LocalDate.now());

        voyageStatutRepo.save(voyagestatut);
    }
}

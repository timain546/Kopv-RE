package com.cooperative.transport.services;

import com.cooperative.transport.entities.*;
import com.cooperative.transport.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    // @Transactional
    // public boolean deleteVoyage(Integer id) {
    // if (voyageRepository.existsById(id)) {
    // voyageRepository.deleteById(id);
    // return true;
    // } else {
    // return false;
    // }
    // }

    public void annuler(Voyages voyage) {
        VoyageStatut voyagestatut = new VoyageStatut();
        voyagestatut.setVoyage(voyage);
        Optional<StatutVoyage> statut = statutVoyageRepo.findByLibelle("Annulé");
        StatutVoyage statutAnnule = statut.get();
        voyagestatut.setStatut(statutAnnule);

        voyageStatutRepo.save(voyagestatut);
    }
}

package com.cooperative.transport.repositories;

import com.cooperative.transport.entities.Voyages;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoyageRepository extends JpaRepository<Voyages, Integer> {
    @EntityGraph(attributePaths = {
            "trajet",
            "trajet.gareDepart",
            "trajet.gareArrivee",
            "vehicule",
            "chauffeur"
    })
    @Query("SELECT v FROM Voyages v ORDER BY v.dateHeureDepart ASC")
    List<Voyages> findAllCatalogueVoyage();
}
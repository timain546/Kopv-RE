package com.cooperative.transport.repositories;

import com.cooperative.transport.entities.Voyages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoyageRepository extends JpaRepository<Voyages, Long> {

    @Query("SELECT DISTINCT v FROM Voyages v " +
           "LEFT JOIN FETCH v.voyageStatuts vs " +
           "LEFT JOIN FETCH vs.statut " +
           "LEFT JOIN FETCH v.trajet t " +
           "LEFT JOIN FETCH t.gareDepart " +
           "LEFT JOIN FETCH t.gareArrivee " +
           "LEFT JOIN FETCH v.vehicule " +
           "LEFT JOIN FETCH v.chauffeur " +
           "ORDER BY v.dateHeureDepart DESC")
    List<Voyages> findAllCatalogueVoyage();
}
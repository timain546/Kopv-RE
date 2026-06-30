package com.cooperative.transport.services;

import com.cooperative.transport.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    public List<voyage> getAllVoyages() {
        return voyageRepository.findAllVoyages();
    }
}

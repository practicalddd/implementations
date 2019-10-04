package com.practicalddd.cargotracker.routingms.application.internal.queryservices;


import com.practicalddd.cargotracker.routingms.domain.model.aggregates.Voyage;
import com.practicalddd.cargotracker.routingms.domain.model.entities.CarrierMovement;
import com.practicalddd.cargotracker.routingms.infrastructure.repositories.jpa.VoyageRepository;
import com.practicalddd.cargotracker.shareddomain.model.TransitEdge;
import com.practicalddd.cargotracker.shareddomain.model.TransitPath;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

/**
 * Application Service class for the Cargo Routing Query service
 */
@ApplicationScoped
public class CargoRoutingQueryService {

    @Inject
    private VoyageRepository voyageRepository; // Inject Dependencies

    /**
     * Returns all Voyages
     * @return
     */
    @Transactional
    public List<Voyage> findAll(){
        return voyageRepository.findAll();
    }


}

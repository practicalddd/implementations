package com.practicalddd.cargotracker.trackingms.interfaces.events;

import com.practicalddd.cargotracker.shareddomain.events.CargoBookedEvent;
import fish.payara.micro.cdi.Inbound;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class CargoBookedEventHandler {

    public void testEventObserving(@Observes @Inbound CargoBookedEvent event) {
        // Processing of an event
        System.out.println("***Observed Cargo Booked Event. Doing nothing***"+event.getId());
    }
}

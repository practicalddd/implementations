package com.practicalddd.cargotracker.trackingms.interfaces.events;

import com.practicalddd.cargotracker.shareddomain.events.CargoBookedEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CargoBookedEventHandler {

    public void testEventObserving(CargoBookedEvent event) {
        // Processing of an event
        System.out.println("***Observed Cargo Booked Event. Doing nothing***"+event.getId());
    }
}

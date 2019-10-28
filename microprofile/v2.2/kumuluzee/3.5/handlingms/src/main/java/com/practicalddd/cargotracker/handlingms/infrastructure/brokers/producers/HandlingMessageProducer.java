package com.practicalddd.cargotracker.handlingms.infrastructure.brokers.producers;


import com.kumuluz.ee.amqp.common.annotations.AMQPProducer;
import com.practicalddd.cargotracker.shareddomain.events.*;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HandlingMessageProducer {

    @AMQPProducer(host = "CargoTrackerMQ", exchange = "cargotracker.cargohandlings", key = "cargohandlings")
    public CargoHandledEvent sendCargoHandledEvent(CargoHandledEventData cargoHandledEventData) {
        CargoHandledEvent cargoHandledEvent = new CargoHandledEvent();
        cargoHandledEvent.setContent(cargoHandledEventData);
        System.out.println("***Sending Cargo Routed Event***:()");
        return cargoHandledEvent;
    }


}

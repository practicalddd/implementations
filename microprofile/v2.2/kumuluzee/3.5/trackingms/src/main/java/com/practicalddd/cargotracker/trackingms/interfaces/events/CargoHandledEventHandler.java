package com.practicalddd.cargotracker.trackingms.interfaces.events;

import com.kumuluz.ee.amqp.common.annotations.AMQPConsumer;
import com.kumuluz.ee.amqp.common.annotations.AMQPProducer;
import com.practicalddd.cargotracker.shareddomain.events.CargoHandledEvent;
import com.practicalddd.cargotracker.shareddomain.events.CargoHandledEventData;
import com.practicalddd.cargotracker.trackingms.application.internal.commandservices.AssignTrackingIdCommandService;
import com.practicalddd.cargotracker.trackingms.interfaces.events.transform.TrackingActivityCommandEventAssembler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class CargoHandledEventHandler {

        private AssignTrackingIdCommandService assignTrackingIdCommandService; // Application Service Dependency

        /**
         * Provide the dependencies
         * @param assignTrackingIdCommandService
         */
        @Inject
        public CargoHandledEventHandler(AssignTrackingIdCommandService assignTrackingIdCommandService){
            this.assignTrackingIdCommandService = assignTrackingIdCommandService;
        }


        /**
         * Cargo Handled Event handler
         * @param event
         */
        @Transactional
        @AMQPConsumer(host = "CargoMQ", exchange = "cargoExchange", key = "object")
        public void observeCargoHandledEvent(CargoHandledEvent event) {
                System.out.println("***Observed Cargo Handled Event****"+event.getContent());
                CargoHandledEventData eventData = event.getContent();
                System.out.println(eventData.getBookingId());
                System.out.println(eventData.getHandlingLocation());
                System.out.println(eventData.getHandlingCompletionTime());
                System.out.println(eventData.getHandlingType());
                System.out.println(eventData.getVoyageNumber());
            assignTrackingIdCommandService.addTrackingEvent(
                    TrackingActivityCommandEventAssembler.toCommandFromEvent(event));
        }


}

package com.practicalddd.cargotracker.bookingms.infrastructure.brokers.producers;


import com.kumuluz.ee.amqp.common.annotations.AMQPProducer;
import com.practicalddd.cargotracker.shareddomain.events.CargoBookedEvent;
import com.practicalddd.cargotracker.shareddomain.events.CargoRoutedEvent;
import com.practicalddd.cargotracker.shareddomain.events.CargoRoutedEventData;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookingMessageProducer {

    @AMQPProducer(host = "CargoMQ", exchange = "cargoExchange", key = "object")
    public CargoBookedEvent sendCargoBookedEvent(String bookingId) {
        System.out.println("***Sending Cargo Booked Event***:()");
        CargoBookedEvent cargoBookedEvent = new CargoBookedEvent();
        cargoBookedEvent.setId(bookingId);
        return cargoBookedEvent;
    }

    @AMQPProducer(host = "CargoMQ", exchange = "cargoExchange", key = "object")
    public CargoRoutedEvent sendCargoRoutedEvent(String bookingId) {
        System.out.println("***Sending Cargo Routed Event***:()");
        CargoRoutedEvent cargoRoutedEvent = new CargoRoutedEvent();
        CargoRoutedEventData eventData = new CargoRoutedEventData();
        eventData.setBookingId(bookingId);
        cargoRoutedEvent.setContent(eventData);
        return cargoRoutedEvent;
    }


}

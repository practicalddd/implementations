# Axon Framework Implementation (v4.2) with Axon Server Standard Edition (v4.2)

This Chapter contains a complete DDD implementation of the Cargo Tracker application utilizing the Axon Framework and the Axon Server Standard Edition v4.2

The implementation adopts a CQRS / Event Sourcing / Microservices based architectural style and uses the following technologies

  - Axon Framework as the CQRS framework
  - Axon Server SE as the event source / store
  - Axon Server SE as the event broker
  
The documentation covers the setup and testing process needed to run the microservices correctly. The details are given for each separate microservice (Booking / Routing / Tracking and Handling)

# Axon Server SE setup

Axon Server SE can be downloaded from the website (https://axoniq.io/download) . Running it is as simple as running a jar file (java -jar axonserver.jar). Access the following URL from the browser (http://localhost:8024) to check if the server is running OK. While all events are stored in Axon's purpose built event store database, the projections are built on top of MySql Schemas.

# Test Case

The test case is as follows

- A Cargo is booked to be delivered from Hong Kong to New York with the delivery deadline of 28 September 2019
- Based on the specifications the Cargo is routed accordingly by assigning an itinierary
- The Cargo is handled at the various ports of the itinerary and is finally claimed by the customer
- The customer can track the cargo at any point of time with a unique Tracking Number


# Microservices

Booking MS

    This MS takes care of all the operations associated with the booking of the Cargo. It stores events in Axon's event store and builds a projection for the Cargo Details (Cargo Summary) on a MySql Database
    
    Server Port -> 8081
    Schema Name -> bookingquerymsdb (user: bookingquerymsdb / pw: bookingquerymsdb)
    Tables ->
    
    ##Cargo Table DDL
	CREATE TABLE `cargo_summary_projection` (
  		`booking_id` varchar(20) NOT NULL,
  		`transport_status` varchar(100) NOT NULL,
  		`routing_status` varchar(100) NOT NULL,
  		`spec_origin_id` varchar(100) NOT NULL,
  		`spec_destination_id` varchar(100) NOT NULL,
  		`deadline` date DEFAULT NULL,
  		PRIMARY KEY (`booking_id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
    
   
    Run command -> java -jar bookingms-1.0.jar
    
    JSON Requests (Test via Postman) ->
    
    
    Cargo Booking (http://localhost:8081/cargobooking)
    --------------------------------------------------

    {
        "bookingAmount": 100,
        "originLocation": "CNHKG",
        "destLocation" : "USNYC",
        "destArrivalDeadline" : "2019-09-28"
    }
    


  Routing MS

    This MS takes care of all the operations associated with the routing of the Cargo. 
    The MySql Schemas have to be setup before running this microservice
    
    Server Port -> 8083
    Schema Name -> routingmsdb (user: routingmsdb / pw: routingmsdb)
    Tables ->
    
    ##Voyage Table DDL
    CREATE TABLE `voyage` (
  	`Id` int(11) NOT NULL AUTO_INCREMENT,
  	`voyage_number` varchar(20) NOT NULL,
  	PRIMARY KEY (`Id`)
	) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    ##Carrier Movement Table DDL -
    CREATE TABLE `carrier_movement` (
	  `Id` int(11) NOT NULL AUTO_INCREMENT,
	  `arrival_location_id` varchar(100) DEFAULT NULL,
	  `departure_location_id` varchar(100) DEFAULT NULL,
	  `voyage_id` int(11) DEFAULT NULL,
	  `arrival_date` date DEFAULT NULL,
	  `departure_date` date DEFAULT NULL,
	  PRIMARY KEY (`Id`)
	) ENGINE=InnoDB AUTO_INCREMENT=1358 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    ### Data to ensure Routing works fine ->
    	insert voyage (Id,voyage_number) values(3,'0100S');
	insert voyage (Id,voyage_number) values(4,'0101S');
	insert voyage (Id,voyage_number) values(5,'0102S');

	insert into carrier_movement (Id,arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values (1355,'CNHGH','CNHKG',3,'2019-08-28','2019-08-25');
	insert into carrier_movement (Id,arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values (1356,'JNTKO','CNHGH',4,'2019-09-10','2019-09-01');
	insert into carrier_movement (Id,arrival_location_id,departure_location_id,voyage_id,arrival_date,departure_date) 		values (1357,'USNYC','JNTKO',5,'2019-09-25','2019-09-15');

    Run command -> java -jar routingms-1.0.jar
    
    
Tracking MS

    This MS takes care of all the tracking operations associated with the Cargo. It stores events in Axon's event store and 	builds a projection for the Tracking Details (Tracking Projection) on a MySql Database. The MySql Schemas have to be setup 	before running this microservice
    
    Server Port -> 8084
    Schema Name -> trackingmsdb (user: trackingmsdb/pw:trackingmsdb)
    Tables ->
    
    ##Tracking_activity DDL
   	 CREATE TABLE `tracking_activity` (
	  `Id` int(11) NOT NULL AUTO_INCREMENT,
	  `tracking_number` varchar(20) NOT NULL,
	  `booking_id` varchar(20) DEFAULT NULL,
	  PRIMARY KEY (`Id`)
	) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    ##Tracking_handling_events DDL
	  CREATE TABLE `tracking_handling_events` (
	  `Id` int(11) NOT NULL AUTO_INCREMENT,
	  `tracking_id` int(11) DEFAULT NULL,
	  `event_type` varchar(225) DEFAULT NULL,
	  `event_time` timestamp NULL DEFAULT NULL,
	  `location_id` varchar(100) DEFAULT NULL,
	  `voyage_number` varchar(20) DEFAULT NULL,
	  PRIMARY KEY (`Id`)
	) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
    Run command -> java -jar trackingms-1.0-SNAPSHOT.jar
    

Handling MS
    
    This MS takes care of all the handling operations associated with the Cargo. It does not have any projections so only 	events are generated and stored in Axon's event store
    
    Server Port -> 8082
    
    Run command -> java -jar handlingms-1.0.jar
    
    JSON Requests (Test via Postman) ->
     
    Cargo Handling (http://localhost:8084/cargohandling)
    --------------------------------------------------
    
    Run in sequence
    
    Recieved at port
    {
	    "bookingId" : "<<BookingId>>",
	    "unLocode" : "CNHKG",
	    "handlingType" : "RECEIVE",
	    "completionTime": "2019-08-23",
	    "voyageNumber" : ""
    }
    
    Loaded onto carrier
    {
	    "bookingId" : "<<BookingId>>",
	    "unLocode" : "CNHKG",
	    "handlingType" : "LOAD",
	    "completionTime": "2019-08-25",
	    "voyageNumber" : "0100S"
    }
    
    Unloaded
    {
	    "bookingId" : "<<BookingId>>",
	    "unLocode" : "CNHGH",
	    "handlingType" : "UNLOAD",
	    "completionTime": "2019-08-28",
	    "voyageNumber" : "0100S"
    }
    
    Loaded onto next carrier
    {
	    "bookingId" : "<<BookingId>>",
	    "unLocode" : "CNHGH",
	    "handlingType" : "LOAD",
	    "completionTime": "2019-09-01",
	    "voyageNumber" : "0101S"
    }
    
    Unloaded
    {
	    "bookingId" : "<<BookingId>>",
	    "unLocode" : "JNTKO",
	    "handlingType" : "UNLOAD",
	    "completionTime": "2019-09-10",
	    "voyageNumber" : "0101S"
    }
    
    Loaded onto next carrier
    {
	    "bookingId" : "<<BookingId>>",
	    "unLocode" : "JNTKO",
	    "handlingType" : "LOAD",
	    "completionTime": "2019-09-15",
	    "voyageNumber" : "0102S"
    }
    
    Unloaded
    {
	    "bookingId" : "<<BookingId>>",
	    "unLocode" : "USNYC",
	    "handlingType" : "UNLOAD",
	    "completionTime": "2019-09-25",
	    "voyageNumber" : "0102S"
    }
    
    Customs
    {
	    "bookingId" : "<<BookingId>>",
	    "unLocode" : "USNYC",
	    "handlingType" : "CUSTOMS",
	    "completionTime": "2019-09-26",
	    "voyageNumber" : ""
    }
    
    Claimed
    {
	    "bookingId" : "<<BookingId>>",
	    "unLocode" : "USNYC",
	    "handlingType" : "CLAIM",
	    "completionTime": "2019-09-28",
	    "voyageNumber" : ""
    }
    
    

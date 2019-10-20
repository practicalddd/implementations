# Introduction

This contains Practical DDD implementations for the Cargo Tracker Application across multiple programming languages and frameworks. It aims to demonstrate DDD concepts in the context of an execution platform. For a detailed walkthrough of the implementations please purchase the book -> https://www.amazon.com/s?k=practical+domain-driven+design+in+enterprise+java&crid=1R1SOSED7GAXW&sprefix=practical+domain%2Caps%2C389&ref=nb_sb_ss_i_1_16

# Cargo Tracker Application

The Cargo Tracker Application acts as the reference application for the DDD implementations. In terms of DDD concepts there are 4 main sub-domains of the Cargo Tracker application - Booking , Tracking , Routing and Handling. The sub-domains are solutioned as Bounded Contexts.

  *In case of a modulithic implementation, the Bounded Contexts are individual modules within the monolith
  *In case of a microservices implementation, each Bounded Context may contain a single/set of microservices centered around the root aggregate
  *In case of a CQRS/ES implementation (Axon Framework), the Bounded Contexts may either be implemented as microservices or modules within a monolith. The implementation detail will detail the same
  
 # Cargo Tracker Use Cases
  
  A high level summary of the use cases is given below. The various Commands / Queries and Events are listed below.
  
  *Booking Bounded Context* - This deals with all aspects of Booking Business Capabilities of the Cargo Tracker Application
  
    Commands
      - Book a new Cargo
      - Route a booked Cargo
    Queries
      - Retrieve Cargo Details basis the Booking Number
    Events (Producer)
      - Cargo Booked
      - Cargo Routed
  
  
  *Tracking Bounded Context* - This deals with all aspects of Tracking Business Capabilities of the Cargo Tracker Application
  
    Commands
      - Assign Tracking Number to a Routed Cargo
    Queries
      - Retrieve Cargo Details basis the Tracking Number
    Events (Consumer)
      - Cargo Booked
      - Cargo Routed
      - Cargo Handled
      
   *Handling Bounded Context* - This deals with all aspects of Handling Business Capabilities of the Cargo Tracker Application
  
    Commands
      - Handle a Cargo
    Queries
      - Get list of Handling Events of a Cargo
    Events (Producer)
      - Cargo Handled
      
   *Routing Bounded Context* - This deals with all aspects of Routing Business Capabilities of the Cargo Tracker Application
  
    Commands
      - Create a Voyage
    Queries
      - Get Optimal Route for a Cargo
    Events (Producer/Consumer)
      - None

# Implementations

The list below shows the current implementations available and what will be made available in the coming weeks. This is a 52-week exercise, so please check back for regular updates. 

Week 1 (*Available*) - Spring Boot (Tomcat) + Spring Data + Spring Cloud + MySql + RabbitMQ 

Week 2 (*Available*) - Jakarta EE 8 (Open Liberty 19.0.0.9) + MySql

Week 3 (*Available*) - MicroProfile 3.0 (Helidon v1.3.1) + Oracle DB Autonomous Cloud + RabbitMQ

Week 4 (*Available*) - MicroProfile 2.2 (Payara Micro) + MySql + Payara Clustered Events

Week 5 (*Available*) - Axon Framework 4.2 + Axon Server 4.2 SE

Week 6 (*In Progress*) - Jakarta EE 8 (Payara Server) + MySql

Week 8 (*Planned*) - dapr.io

Week 9 (*Planned*) - MicroProfile 3.0 + MySql + RabbitMQ




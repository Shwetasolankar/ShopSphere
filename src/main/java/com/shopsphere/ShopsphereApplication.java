package com.shopsphere;

// Defines the main package of our ShopSphere application.

import org.springframework.boot.SpringApplication;

// Provides the method used to start the Spring Boot application.

import org.springframework.boot.autoconfigure.SpringBootApplication;

// Enables Spring Boot auto-configuration and component scanning.

@SpringBootApplication

// Tells Spring Boot that this is the main application class.

public class ShopsphereApplication {

    // Defines the main application class.

    public static void main(String[] args) {

        // Main method.
        // Java starts executing the application from here.

        SpringApplication.run(ShopsphereApplication.class, args);

        // Starts the Spring Boot application.
        // It also starts the embedded Tomcat web server.
    }
}
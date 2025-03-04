package com.javaacademy.org.flat_rent;

import com.javaacademy.org.flat_rent.service.AdvertService;
import com.javaacademy.org.flat_rent.service.ApartmentService;
import com.javaacademy.org.flat_rent.service.BookingService;
import com.javaacademy.org.flat_rent.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FlatRentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FlatRentApplication.class, args);
        ApartmentService apartmentService = context.getBean(ApartmentService.class);
        AdvertService advertService = context.getBean(AdvertService.class);
        BookingService bookingService = context.getBean(BookingService.class);
        ClientService clientService = context.getBean(ClientService.class);
    }

}

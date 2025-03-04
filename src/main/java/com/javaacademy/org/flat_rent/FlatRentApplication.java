package com.javaacademy.org.flat_rent;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.service.AdvertService;
import com.javaacademy.org.flat_rent.service.ApartmentService;
import com.javaacademy.org.flat_rent.service.BookingService;
import com.javaacademy.org.flat_rent.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class FlatRentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FlatRentApplication.class, args);
        ApartmentService apartmentService = context.getBean(ApartmentService.class);
        AdvertService advertService = context.getBean(AdvertService.class);
        BookingService bookingService = context.getBean(BookingService.class);
        ClientService clientService = context.getBean(ClientService.class);
//        clientService.saveClient(ClientDto.builder()
//                .name("ivan")
//                .email("email.ru")
//                .build()
//        );
//        apartmentService.saveApartment(ApartmentDto.builder()
//                .city("Moskow")
//                .street("Lenina")
//                .house("dom 1")
//                .apartmentType(ApartmentType.FOUR_ROOM_FLAT)
//                .build());
        System.out.println(advertService.saveAdvert(AdvertDto.builder()
                .price(BigDecimal.TEN)
                .description("text description2222")
                .isActive(true)
                .apartmentId(1)
                .build()));

        System.out.println(bookingService.save(BookingDto.builder()
                .advertId(1)
                .clientId(1)
                .startDate(LocalDateTime.parse("2023-03-02T12:00:00"))
                .endDate(LocalDateTime.parse("2023-03-02T23:00:00"))
                .amount(BigDecimal.TEN)
                .build()
        ));

    }

}

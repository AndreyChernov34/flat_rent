package com.javaacademy.org.flat_rent;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.dto.BookingDtoRs;
import com.javaacademy.org.flat_rent.dto.ClientDto;
import com.javaacademy.org.flat_rent.entity.ApartmentType;
import com.javaacademy.org.flat_rent.repository.BookingRepository;
import com.javaacademy.org.flat_rent.service.AdvertService;
import com.javaacademy.org.flat_rent.service.ApartmentService;
import com.javaacademy.org.flat_rent.service.BookingService;
import com.javaacademy.org.flat_rent.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(value = "classpath:clear-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ClientControllerTest {
    @Autowired
    private ClientService clientService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private AdvertService advertService;

    @Test
    @DisplayName("При удалении клиента, проверить что удаляются его брони")
    public void testDeleteClientById() {
        ClientDto clientDtoTest = clientService.save(newClientDtoTest());
        BookingDtoRs bookingDtoRsTest1 = bookingService.save(newBookingDtoTest(clientDtoTest,
                LocalDate.of(2025, 3, 5), LocalDate.of(2025, 3, 8)));
        BookingDtoRs bookingDtoRsTest2 = bookingService.save(newBookingDtoTest(clientDtoTest,
                LocalDate.of(2025, 4, 5), LocalDate.of(2025, 4, 8)));

        clientService.deleteById(clientDtoTest.getId());

        assertFalse(bookingRepository.existsById(bookingDtoRsTest1.getId()));
        assertFalse(bookingRepository.existsById(bookingDtoRsTest2.getId()));
    }

    private ClientDto newClientDtoTest() {
        return ClientDto.builder()
                .id(null)
                .email("testEmail")
                .name("testName")
                .build();
    }

    private AdvertDto newAdvertDtoTest() {
        return AdvertDto.builder()
                .id(null)
                .apartmentId(apartmentService.save(newApartmentDtoTest()).getId())
                .isActive(true)
                .description("Test description")
                .price(BigDecimal.valueOf(1000))
                .build();
    }

    private ApartmentDto newApartmentDtoTest() {
        return ApartmentDto.builder()
                .city("testCity")
                .street("testStreet")
                .house("testHouse")
                .apartmentType(ApartmentType.FOUR_ROOM_FLAT)
                .build();
    }

    private BookingDto newBookingDtoTest(ClientDto clientDto, LocalDate startDate, LocalDate endDate) {
        return BookingDto.builder()
                .id(null)
                .clientDto(clientDto)
                .advertId(advertService.save(newAdvertDtoTest()).getId())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}

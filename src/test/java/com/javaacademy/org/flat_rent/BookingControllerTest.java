package com.javaacademy.org.flat_rent;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.dto.BookingDtoRs;
import com.javaacademy.org.flat_rent.dto.ClientDto;
import com.javaacademy.org.flat_rent.entity.ApartmentType;
import com.javaacademy.org.flat_rent.service.AdvertService;
import com.javaacademy.org.flat_rent.service.ApartmentService;
import com.javaacademy.org.flat_rent.service.BookingService;
import com.javaacademy.org.flat_rent.service.ClientService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(value = "classpath:clear-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class BookingControllerTest {
    @Autowired
    private AdvertService advertService;
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BookingService bookingService;

    private BookingDto bookingDtoTest;
    private ClientDto clientDtoTest;


    @LocalServerPort
    private int port;

    private ClientDto newClientDtoTest() {
        return ClientDto.builder()
                .id(null)
                .email("testEmail")
                .name("testName")
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

    private AdvertDto newAdvertDtoTest() {
        return AdvertDto.builder()
                .id(null)
                .apartmentId(apartmentService.save(newApartmentDtoTest()).getId())
                .isActive(true)
                .description("Test description")
                .price(BigDecimal.valueOf(1000))
                .build();
    }

    private BookingDto newBookingDtoTest(LocalDate startDate, LocalDate endDate) {
        return BookingDto.builder()
                .id(null)
                .clientDto(newClientDtoTest())
                .advertId(advertService.save(newAdvertDtoTest()).getId())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    @Test
    @DisplayName("Успешное бронирование, при незаполненности id у клиента. Проверить что создан новый клиент.")
    public void saveBookingWithNullIdClientSuccess() {
        RestAssured.port = port;
        bookingDtoTest = BookingDto.builder()
                .id(null)
                .advertId(advertService.save(newAdvertDtoTest()).getId())
                .startDate(LocalDate.of(2025, 03, 01))
                .endDate(LocalDate.of(2025, 03, 07))
                .clientDto(newClientDtoTest())
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bookingDtoTest)
                .when()
                .post("/booking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("date_start", equalTo(bookingDtoTest.getStartDate().toString()))
                .body("date_finish", equalTo(bookingDtoTest.getEndDate().toString()))
                .body("client.id", notNullValue())
                .body("client.name", equalTo(bookingDtoTest.getClientDto().getName()))
                .body("client.email", equalTo(bookingDtoTest.getClientDto().getEmail()))
                .body("advert.id", equalTo(bookingDtoTest.getAdvertId()))
                .body("result_price", notNullValue());
    }

    @Test
    @DisplayName("Успешное бронирование, при указанном id у клиента.")
    public void saveBookingWithNonNullIdClientSuccess() {
        RestAssured.port = port;
        bookingDtoTest = BookingDto.builder()
                .id(null)
                .advertId(advertService.save(newAdvertDtoTest()).getId())
                .startDate(LocalDate.of(2025, 03, 01))
                .endDate(LocalDate.of(2025, 03, 07))
                .clientDto(clientService.save(newClientDtoTest()))
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bookingDtoTest)
                .when()
                .post("/booking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("date_start", equalTo(bookingDtoTest.getStartDate().toString()))
                .body("date_finish", equalTo(bookingDtoTest.getEndDate().toString()))
                .body("client.id", equalTo(bookingDtoTest.getClientDto().getId()))
                .body("client.name", equalTo(bookingDtoTest.getClientDto().getName()))
                .body("client.email", equalTo(bookingDtoTest.getClientDto().getEmail()))
                .body("advert.id", equalTo(bookingDtoTest.getAdvertId()))
                .body("result_price", notNullValue());
    }

    @Test
    @DisplayName("Неуспешное бронирование при существующем бронировании на эти даты (случай 4.2.2)")
    public void saveBookingFailDate() {

        BookingDtoRs firstBooking = bookingService
                .save(newBookingDtoTest(LocalDate.of(2025, 11, 01),
                        LocalDate.of(2025, 11, 10)));
        bookingDtoTest = newBookingDtoTest(LocalDate.of(2025, 11, 5),
                LocalDate.of(2025, 11, 6
                ));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bookingDtoTest)
                .when()
                .post("/booking")
                .then()
                .statusCode(400);
    }


    @Test
    @DisplayName("Неуспешное бронирование при существующем бронировании на эти даты (случай 4.2.3)")
    public void saveBookingFailAfterDate() {

        BookingDtoRs firstBooking = bookingService
                .save(newBookingDtoTest(LocalDate.of(2025, 11, 1),
                        LocalDate.of(2025, 11, 10)));
        bookingDtoTest = newBookingDtoTest(LocalDate.of(2025, 11, 9),
                LocalDate.of(2025, 11, 20
                ));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bookingDtoTest)
                .when()
                .post("/booking")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Неуспешное бронирование при существующем бронировании на эти даты (случай 4.2.4)")
    public void saveBookingFailBeforeDate() {

        BookingDtoRs firstBooking = bookingService
                .save(newBookingDtoTest(LocalDate.of(2025, 10, 1),
                        LocalDate.of(2025, 11, 10)));
        bookingDtoTest = newBookingDtoTest(LocalDate.of(2025, 9, 20),
                LocalDate.of(2025, 10, 2));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bookingDtoTest)
                .when()
                .post("/booking")
                .then()
                .statusCode(400);
    }
}

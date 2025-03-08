package com.javaacademy.org.flat_rent;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.entity.ApartmentType;
import com.javaacademy.org.flat_rent.repository.ApartmentRepository;
import com.javaacademy.org.flat_rent.service.AdvertService;
import com.javaacademy.org.flat_rent.service.ApartmentService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

@Sql(value = "classpath:clear-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AdvertControllerTest {
    @Autowired
    private AdvertService advertService;
    @Autowired
    private ApartmentService apartmentService;

    private AdvertDto advertDtoTest;
    private ApartmentDto apartmentDtoTest;

    @LocalServerPort
    private int port;

    @Test
    public void saveAdvertSuccess() {
        RestAssured.port = port;
        apartmentDtoTest = apartmentService.save(ApartmentDto.builder()
                .house("12")
                .street("Lenina")
                .city("Mosсow")
                .apartmentType(ApartmentType.FOUR_ROOM_FLAT)
                .build());

        advertDtoTest = AdvertDto.builder()
                .id(null)
                .apartmentId(apartmentDtoTest.getId())
                .isActive(true)
                .description("Test description2")
                .price(BigDecimal.valueOf(1000))
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(advertDtoTest)
                .when()
                .post("/advert")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("price", equalTo(advertDtoTest.getPrice().intValue()))
                .body("is_active", equalTo(advertDtoTest.getIsActive()))
                .body("apartment.id", equalTo(advertDtoTest.getApartmentId()))
                .body("description", equalTo(advertDtoTest.getDescription()));
    }
}

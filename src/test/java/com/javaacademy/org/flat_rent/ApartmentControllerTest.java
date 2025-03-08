package com.javaacademy.org.flat_rent;

import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.entity.ApartmentType;
import com.javaacademy.org.flat_rent.service.ApartmentService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(value = "classpath:clear-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ApartmentControllerTest {
    private ApartmentService apartmentService;
    private ApartmentDto apartmentDtoTest;

    @LocalServerPort
    private int port;

    @Test
    public void saveApartmentSuccess() {
        RestAssured.port = port;
        apartmentDtoTest = ApartmentDto.builder()
                .city("Moskow")
                .street("Lenina")
                .house("11")
                .apartmentType(ApartmentType.FOUR_ROOM_FLAT)
                .build();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(apartmentDtoTest)
                .when()
                .post("/apartment")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("city", equalTo(apartmentDtoTest.getCity()))
                .body("street", equalTo(apartmentDtoTest.getStreet()))
                .body("house", equalTo(apartmentDtoTest.getHouse()))
                .body("apartment_type", equalTo(apartmentDtoTest.getApartmentType().name()));
    }
}

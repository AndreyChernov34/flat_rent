package com.javaacademy.org.flat_rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaacademy.org.flat_rent.entity.ApartmentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ApartmentDto", description = "DTO для помещения")
public class ApartmentDto {
    private Integer id;
    private String city;
    private String street;
    private String house;
    @JsonProperty("apartment_type")
    private ApartmentType apartmentType;
}

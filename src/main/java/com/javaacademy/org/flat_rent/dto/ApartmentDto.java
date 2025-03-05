package com.javaacademy.org.flat_rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaacademy.org.flat_rent.entity.ApartmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDto {
    private Integer id;
    private String city;
    private String street;
    private String house;
    @JsonProperty("apartment_type")
    private ApartmentType apartmentType;
}

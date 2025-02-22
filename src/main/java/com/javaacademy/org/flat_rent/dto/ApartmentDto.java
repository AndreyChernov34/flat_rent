package com.javaacademy.org.flat_rent.dto;

import com.javaacademy.org.flat_rent.entity.ApartmentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApartmentDto {

    private String city;
    private String street;
    private String house;
    private ApartmentType apartmentType;
}

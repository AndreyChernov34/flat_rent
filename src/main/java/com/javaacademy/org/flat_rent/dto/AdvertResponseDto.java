package com.javaacademy.org.flat_rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class AdvertResponseDto {
    private Integer id;
    private BigDecimal price;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("apartment")
    private ApartmentDto apartmentDto;
    private String description;
}

package com.javaacademy.org.flat_rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BookingResponseDto {
    private Integer id;
    @JsonProperty("date_start")
    private LocalDateTime startDate;
    @JsonProperty("date_finish")
    private LocalDateTime endDate;
    @JsonProperty("client")
    private ClientDto clientDto;
    @JsonProperty("advert")
    private AdvertResponseDto advertResponseDto;
    @JsonProperty("result_price")
    private BigDecimal amount;
}

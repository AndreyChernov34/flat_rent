package com.javaacademy.org.flat_rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BookingDto {
    private Integer id;
    @JsonProperty("date_start")
    private LocalDateTime startDate;
    @JsonProperty("date_finish")
    private LocalDateTime endDate;
    @JsonProperty("client_id")
    private Integer clientId;
    @JsonProperty("advert_id")
    private Integer advertId;
    @JsonProperty("result_price")
    private BigDecimal amount;
}

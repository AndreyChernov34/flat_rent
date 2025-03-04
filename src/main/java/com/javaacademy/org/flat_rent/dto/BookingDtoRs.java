package com.javaacademy.org.flat_rent.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtoRs {
    private Integer id;

    @JsonProperty("date_start")
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDateTime startDate;

    @JsonProperty("date_finish")
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDateTime endDate;

    @JsonProperty("client")
    private ClientDto clientDto;

    @JsonProperty("advert")
    private AdvertDtoRs advertDtoRs;

    @JsonProperty("result_price")
    private BigDecimal amount;
}

package com.javaacademy.org.flat_rent.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Integer id;

    @JsonProperty("date_start")
    private LocalDate startDate;

    @JsonProperty("date_finish")
    private LocalDate endDate;

    @JsonProperty("client_id")
    private Integer clientId;

    @JsonProperty("advert_id")
    private Integer advertId;
}

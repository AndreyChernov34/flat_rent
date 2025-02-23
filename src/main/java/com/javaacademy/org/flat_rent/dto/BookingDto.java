package com.javaacademy.org.flat_rent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javaacademy.org.flat_rent.entity.Advert;
import com.javaacademy.org.flat_rent.entity.Client;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
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

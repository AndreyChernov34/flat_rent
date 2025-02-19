package com.javaacademy.org.flat_rent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDateBooking;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDateBooking;

    @Column(name = "id_name", nullable = false)
    private Integer idClient;

    @Column(name = "id_advert", nullable = false)
    private Integer idAdvert;

    @Column(name = "amount", nullable = false)
    private BigDecimal amountBooking;
}

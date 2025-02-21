package com.javaacademy.org.flat_rent.repository;

import com.javaacademy.org.flat_rent.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepostory extends JpaRepository<Booking, Integer> {
}

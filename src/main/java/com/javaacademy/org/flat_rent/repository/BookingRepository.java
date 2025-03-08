package com.javaacademy.org.flat_rent.repository;

import com.javaacademy.org.flat_rent.entity.Booking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByAdvertId(Integer id);
    List<Booking> findByClientEmail(String email, Pageable pageable);

}

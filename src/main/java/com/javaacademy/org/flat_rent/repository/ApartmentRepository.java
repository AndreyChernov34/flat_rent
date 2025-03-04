package com.javaacademy.org.flat_rent.repository;

import com.javaacademy.org.flat_rent.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
}

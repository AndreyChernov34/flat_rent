package com.javaacademy.org.flat_rent.repository;

import com.javaacademy.org.flat_rent.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepostory extends JpaRepository<Apartment, Integer> {
}

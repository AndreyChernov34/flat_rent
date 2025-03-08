package com.javaacademy.org.flat_rent.repository;

import com.javaacademy.org.flat_rent.entity.Advert;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdvertRepository extends JpaRepository<Advert, Integer> {
    List<Advert> findByApartmentCity(String city, Pageable pageable);
    Optional<Advert> findById(Integer id);
}

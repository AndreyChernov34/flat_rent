package com.javaacademy.org.flat_rent.repository;

import com.javaacademy.org.flat_rent.entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<Advert, Integer> {
}

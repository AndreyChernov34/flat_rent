package com.javaacademy.org.flat_rent.repository;

import com.javaacademy.org.flat_rent.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}

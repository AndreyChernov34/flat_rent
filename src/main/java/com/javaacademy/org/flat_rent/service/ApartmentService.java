package com.javaacademy.org.flat_rent.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.mapper.ApartmentMapper;
import com.javaacademy.org.flat_rent.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentMapper apartmentMapper;
    private final ApartmentRepository apartmentRepository;
    private final ObjectMapper objectMapper;

    public ApartmentDto saveApartment(ApartmentDto apartmentDto) {
        return apartmentMapper.toDto(apartmentRepository.save(apartmentMapper.toEntity(apartmentDto)));
    };
}

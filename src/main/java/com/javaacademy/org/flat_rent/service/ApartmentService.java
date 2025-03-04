package com.javaacademy.org.flat_rent.service;

import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.mapper.ApartmentMapper;
import com.javaacademy.org.flat_rent.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentMapper apartmentMapper;
    private final ApartmentRepository apartmentRepository;

    @Transactional
    public ApartmentDto save(ApartmentDto apartmentDto) {
        return apartmentMapper.toDto(apartmentRepository.save(apartmentMapper.toEntity(apartmentDto)));
    }
}

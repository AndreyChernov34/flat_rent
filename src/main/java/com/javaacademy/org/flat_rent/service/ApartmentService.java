package com.javaacademy.org.flat_rent.service;

import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.mapper.ApartmentMapper;
import com.javaacademy.org.flat_rent.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentMapper apartmentMapper;
    private final ApartmentRepository apartmentRepository;

    public ApartmentDto save(ApartmentDto apartmentDto) {
        return apartmentMapper.toDto(apartmentRepository.save(apartmentMapper.toEntity(apartmentDto)));
    }

    public List<ApartmentDto> findByCity(String city) {
        return apartmentMapper.toListDto(apartmentRepository
                .findByCity(city));
    }
}

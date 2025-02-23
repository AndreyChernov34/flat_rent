package com.javaacademy.org.flat_rent.entity.service;

import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.mapper.ApartmentMapper;
import com.javaacademy.org.flat_rent.repository.ApartmentRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentMapper apartmentMapper;
    private final ApartmentRepostory apartmentRepostory;

    public ApartmentDto saveApartment(ApartmentDto apartmentDto) {
        return apartmentMapper.toDto(
                apartmentRepostory.save(apartmentMapper.toEntity(apartmentDto)));
    }

    ;

}

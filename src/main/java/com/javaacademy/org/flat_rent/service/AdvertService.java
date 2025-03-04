package com.javaacademy.org.flat_rent.service;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.AdvertResponseDto;
import com.javaacademy.org.flat_rent.entity.Advert;
import com.javaacademy.org.flat_rent.mapper.AdvertMapper;
import com.javaacademy.org.flat_rent.repository.AdvertRepository;
import com.javaacademy.org.flat_rent.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertMapper advertMapper;
    private final ApartmentRepository apartmentRepository;
    private final AdvertRepository advertRepository;

    @Transactional
    public AdvertResponseDto saveAdvert(AdvertDto advertDto) {
        if (!apartmentRepository.existsById(advertDto.getApartmentId())) {
            throw new RuntimeException("Ошибка в id помещения");
        }
        Advert advert = advertRepository.save(advertMapper.toEntityWithRelation(advertDto));
        return advertMapper.toResponseDto(advert);
    };
}

package com.javaacademy.org.flat_rent.service;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.AdvertDtoRs;
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
    public AdvertDtoRs save(AdvertDto advertDto) {
        Advert advert = advertRepository.save(advertMapper.toEntityWithRelation(advertDto));
        return advertMapper.toDtoRs(advert);
    }
}

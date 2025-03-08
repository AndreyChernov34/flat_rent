package com.javaacademy.org.flat_rent.service;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.AdvertDtoRs;
import com.javaacademy.org.flat_rent.entity.Advert;
import com.javaacademy.org.flat_rent.mapper.AdvertMapper;
import com.javaacademy.org.flat_rent.repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private static final Integer PAGE_LIMIT = 10;
    private final AdvertMapper advertMapper;
    private final AdvertRepository advertRepository;

    public AdvertDtoRs save(AdvertDto advertDto) {
        Advert advert = advertRepository.save(advertMapper.toEntityWithRelation(advertDto));
        return advertMapper.toDtoRs(advert);
    }

    public List<AdvertDtoRs> findByApartmentCity(String city) {
        return advertMapper.toListDtoRs(advertRepository
                .findByApartmentCity(city,
                        PageRequest.of(0, PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "price"))));
    }
}

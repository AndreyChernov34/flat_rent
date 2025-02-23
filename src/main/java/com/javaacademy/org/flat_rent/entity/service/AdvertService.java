package com.javaacademy.org.flat_rent.entity.service;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.ClientDto;
import com.javaacademy.org.flat_rent.mapper.AdvertMapper;
import com.javaacademy.org.flat_rent.mapper.ClientMapper;
import com.javaacademy.org.flat_rent.repository.AdvertRepostory;
import com.javaacademy.org.flat_rent.repository.ClienttRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final AdvertMapper advertMapper;
    private final AdvertRepostory advertRepostory;

    public AdvertDto saveAdvert(AdvertDto advertDto) {
        return advertMapper.toDto(
                advertRepostory.save(advertMapper.toEntity(advertDto)));
    }

    ;
}

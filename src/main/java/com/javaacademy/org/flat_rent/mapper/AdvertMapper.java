package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.entity.Advert;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdvertMapper {
    AdvertDto toDto(Advert advert);

    Advert toEntity(AdvertDto advertDto);
}

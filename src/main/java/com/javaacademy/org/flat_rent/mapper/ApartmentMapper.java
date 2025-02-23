package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.entity.Apartment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApartmentMapper {
    ApartmentDto toDto(Apartment apartment);

    Apartment toEntity(ApartmentDto apartmentDto);
}

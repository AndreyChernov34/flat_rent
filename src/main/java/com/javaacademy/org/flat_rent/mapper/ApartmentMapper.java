package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.entity.Apartment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApartmentMapper {
    ApartmentDto toDto(Apartment apartment);
    List<ApartmentDto> toListDto(List<Apartment> apartments);
    @Mapping(target = "advertList", ignore = true)
    Apartment toEntity(ApartmentDto apartmentDto);
}

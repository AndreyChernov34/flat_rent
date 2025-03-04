package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.AdvertResponseDto;
import com.javaacademy.org.flat_rent.entity.Advert;
import com.javaacademy.org.flat_rent.entity.Apartment;
import com.javaacademy.org.flat_rent.repository.ApartmentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ApartmentMapper.class)
public abstract class AdvertMapper {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Mapping(target = "apartmentDto", source = "apartment")
    public abstract AdvertResponseDto toResponseDto(Advert advert);

    public abstract Advert toEntity(AdvertDto advertDto);

    @
            Mapping(target = "apartment", source = "apartmentId", qualifiedByName = "getApartment")
    public abstract Advert toEntityWithRelation(AdvertDto advertDto);

    @Named("getApartment")
    protected Apartment getApartment(Integer apartmentId) {
        return apartmentRepository.findById(apartmentId).orElseThrow();
    }
}

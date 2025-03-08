package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.AdvertDtoRs;
import com.javaacademy.org.flat_rent.entity.Advert;
import com.javaacademy.org.flat_rent.entity.Apartment;
import com.javaacademy.org.flat_rent.exception.EntityNotFoundException;
import com.javaacademy.org.flat_rent.repository.ApartmentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ApartmentMapper.class)
public abstract class AdvertMapper {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Mapping(target = "apartment", source = "apartmentId", qualifiedByName = "getApartmentById")
    @Mapping(target = "bookingList", ignore = true)
    public abstract Advert toEntityWithRelation(AdvertDto advertDto);

    @Mapping(target = "apartmentDto", source = "apartment")
    public abstract AdvertDtoRs toDtoRs(Advert advert);

    @Mapping(target = "apartmentDto", source = "apartment")
    public abstract List<AdvertDtoRs> toListDtoRs(List<Advert> adverts);

    @Named("getApartmentById")
    protected Apartment getApartmentById(Integer apartmentId) {
        return apartmentRepository.findById(apartmentId).
                orElseThrow(() -> new EntityNotFoundException("не найдено помещение с id = " + apartmentId));
    }
}

package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.dto.BookingResponseDto;
import com.javaacademy.org.flat_rent.entity.Advert;
import com.javaacademy.org.flat_rent.entity.Booking;
import com.javaacademy.org.flat_rent.entity.Client;
import com.javaacademy.org.flat_rent.repository.AdvertRepository;
import com.javaacademy.org.flat_rent.repository.ClientRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AdvertMapper.class, ClientMapper.class})
public abstract class BookingMapper {
    @Autowired
    private AdvertRepository advertRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Mapping(target = "clientDto", source = "client")
    @Mapping(target = "advertResponseDto", source = "advert")
    public abstract BookingResponseDto toResponseDto(Booking booking);

    @Mapping(target = "client", source = "clientId", qualifiedByName = "getClient")
    @Mapping(target = "advert", source = "advertId", qualifiedByName = "getAdvert")
    public abstract Booking toEntityWithRelation(BookingDto bookingDto);

    @Named("getClient")
    protected Client getClient(Integer id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Named("getAdvert")
    protected Advert getAdvert(Integer id) {
        return advertRepository.findById(id).orElseThrow();
    }
}

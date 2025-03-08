package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.dto.BookingDtoRs;
import com.javaacademy.org.flat_rent.entity.Advert;
import com.javaacademy.org.flat_rent.entity.Booking;
import com.javaacademy.org.flat_rent.exception.EntityNotFoundException;
import com.javaacademy.org.flat_rent.repository.AdvertRepository;
import com.javaacademy.org.flat_rent.repository.ClientRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AdvertMapper.class, ClientMapper.class})
public abstract class BookingMapper {
    @Autowired
    private AdvertRepository advertRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Mapping(target = "clientDto", source = "client")
    @Mapping(target = "advertDtoRs", source = "advert")
    @Mapping(target = "resultPrice", source = "amount")
    public abstract BookingDtoRs toDtoRs(Booking booking);

    @Mapping(target = "clientDto", source = "client")
    @Mapping(target = "advertDtoRs", source = "advert")
    @Mapping(target = "resultPrice", source = "amount")
    public abstract List<BookingDtoRs> toListDtoRs(List<Booking> bookings);

    @Mapping(target = "client", source = "clientDto")
    @Mapping(target = "advert", source = "advertId", qualifiedByName = "getAdvertById")
    @Mapping(target = "amount", ignore = true)
    public abstract Booking toEntityWithRelation(BookingDto bookingDto);

//    @Named("getClientById")
//    protected Client getClientById(Integer id) {
//        return clientRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("не найден клиент с id = " + id));
//    }

    @Named("getAdvertById")
    protected Advert getAdvertById(Integer id) {
        return advertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("не найдено объявление с id = " + id));
    }
}

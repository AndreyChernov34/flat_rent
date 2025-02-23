package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.entity.Advert;
import com.javaacademy.org.flat_rent.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {
    BookingDto toDto(Booking booking);

    Booking toEntity(BookingDto bookingDto);
}

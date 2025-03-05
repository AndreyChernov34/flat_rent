package com.javaacademy.org.flat_rent.service;

import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.dto.BookingDtoRs;
import com.javaacademy.org.flat_rent.entity.Booking;
import com.javaacademy.org.flat_rent.mapper.BookingMapper;
import com.javaacademy.org.flat_rent.repository.AdvertRepository;
import com.javaacademy.org.flat_rent.repository.BookingRepository;
import com.javaacademy.org.flat_rent.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final AdvertRepository advertRepository;
    private final ClientRepository clientRepository;

    public BookingDtoRs save(BookingDto bookingDto) {

        Booking booking = bookingMapper.toEntityWithRelation(bookingDto);
        booking.setAmount(calculateResultPrice(booking));

        return bookingMapper.toDtoRs(bookingRepository.save(booking));
    }

    private BigDecimal calculateResultPrice(Booking booking) {

        return booking.getAdvert().getPrice()
                .multiply(BigDecimal.valueOf(
                        ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate())));

    }

}

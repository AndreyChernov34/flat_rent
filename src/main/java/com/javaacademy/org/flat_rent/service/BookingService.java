package com.javaacademy.org.flat_rent.service;

import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.dto.BookingResponseDto;
import com.javaacademy.org.flat_rent.entity.Booking;
import com.javaacademy.org.flat_rent.mapper.BookingMapper;
import com.javaacademy.org.flat_rent.repository.AdvertRepository;
import com.javaacademy.org.flat_rent.repository.BookingRepository;
import com.javaacademy.org.flat_rent.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final AdvertRepository advertRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public BookingResponseDto save(BookingDto bookingDto) {
        if (!advertRepository.existsById(bookingDto.getAdvertId())) {
            throw new RuntimeException("Объявление не найдено");
        }
        if (!clientRepository.existsById(bookingDto.getClientId())) {
            throw new RuntimeException("Клиент не найден");
        }
        Booking booking = bookingMapper.toEntityWithRelation(bookingDto);

        return bookingMapper.toResponseDto(bookingRepository.save(booking));
    }

}

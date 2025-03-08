package com.javaacademy.org.flat_rent.service;

import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.dto.BookingDtoRs;
import com.javaacademy.org.flat_rent.dto.ClientDto;
import com.javaacademy.org.flat_rent.entity.Booking;
import com.javaacademy.org.flat_rent.exception.DateCrossException;
import com.javaacademy.org.flat_rent.exception.EntityNotFoundException;
import com.javaacademy.org.flat_rent.mapper.BookingMapper;
import com.javaacademy.org.flat_rent.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final static Integer PAGE_LIMIT = 20;
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final ClientService clientService;


    @Transactional
    public BookingDtoRs save(BookingDto bookingDto) {
        System.out.println(bookingDto.getClientDto().toString());


        if (bookingDto.getClientDto().getId() == null) {
            ClientDto clientDto = clientService.save(ClientDto.builder()
                    .email(bookingDto.getClientDto().getEmail())
                    .name(bookingDto.getClientDto().getName())
                    .build());
            bookingDto.setClientDto(clientDto);
        } else if (!clientService.existsById(bookingDto.getClientDto().getId())) {
            throw new EntityNotFoundException("не найден клиент с id = " + bookingDto.getClientDto().getId());
        }

        List<Booking> bookings = bookingRepository.findByAdvertId(bookingDto.getAdvertId());

        for (Booking booking : bookings) {
            if (isCrossPeriod(bookingDto.getStartDate(), bookingDto.getEndDate(),
                    booking.getStartDate(), booking.getEndDate())) {
                throw new DateCrossException("данный период бронирования уже занят");
            }
        }

        Booking booking = bookingMapper.toEntityWithRelation(bookingDto);
        booking.setAmount(calculateResultPrice(booking));
        return bookingMapper.toDtoRs(bookingRepository.save(booking));
    }

    public List<BookingDtoRs> findByClientEmail(String email) {
        List<Booking> bookings = bookingRepository.findByClientEmail(email,
                (Pageable) PageRequest.of(0, PAGE_LIMIT));
        return bookingMapper.toListDtoRs(bookings);
    }

    private BigDecimal calculateResultPrice(Booking booking) {
        return booking.getAdvert().getPrice()
                .multiply(BigDecimal.valueOf(
                        ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate())));
    }

    private Boolean isCrossPeriod(
            LocalDate startPeriod1, LocalDate endPeriod1, LocalDate startPeriod2, LocalDate endPeriod2) {
        return (
                (((startPeriod1.isAfter(startPeriod2)) || (startPeriod1.isEqual(startPeriod2)))
                        && ((startPeriod1.isBefore(endPeriod2)) || (startPeriod1.isEqual(endPeriod2))))
                        || (((endPeriod1.isAfter(startPeriod2)) || (endPeriod1.isEqual(startPeriod2)))
                        && ((endPeriod1.isBefore(endPeriod2)) || (endPeriod1.isEqual(endPeriod2))))
                        || (((startPeriod1.isBefore(startPeriod2)) || (startPeriod1.isEqual(startPeriod2)))
                        && ((endPeriod1.isAfter(endPeriod2)) || (endPeriod1.isEqual(endPeriod2))))
        );
    }
}

package com.javaacademy.org.flat_rent.contoller;

import com.javaacademy.org.flat_rent.dto.BookingDto;
import com.javaacademy.org.flat_rent.dto.BookingDtoRs;
import com.javaacademy.org.flat_rent.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
@CrossOrigin
@Tag(name = "BookingController", description = "Контроллер для работы с бронированиями")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    @Operation(summary = "Создание нового бронирования")
    public ResponseEntity<BookingDtoRs> save(@RequestBody BookingDto bookingDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.save(bookingDto));
    }

    @GetMapping("{email}")
    @Operation(summary = "Поиск объявлений по email пользователя")
    public ResponseEntity<List<BookingDtoRs>> findByClientEmail(@PathVariable String email) {
        return ResponseEntity.ok(bookingService.findByClientEmail(email));
    }
}

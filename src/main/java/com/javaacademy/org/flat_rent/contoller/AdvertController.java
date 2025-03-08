package com.javaacademy.org.flat_rent.contoller;

import com.javaacademy.org.flat_rent.dto.AdvertDto;
import com.javaacademy.org.flat_rent.dto.AdvertDtoRs;
import com.javaacademy.org.flat_rent.service.AdvertService;
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
@RequestMapping("/advert")
@CrossOrigin
@Tag(name = "AdvertController", description = "Контроллер для работы с объявлениями")
public class AdvertController {
    private final AdvertService advertService;

    @PostMapping
    @Operation(summary = "Создание нового объявления")
    public ResponseEntity<AdvertDtoRs> save(@RequestBody AdvertDto advertDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(advertService.save(advertDto));
    }


    @GetMapping("{city}")
    @Operation(summary = "Поиск объявлений по городу")
    public ResponseEntity<List<AdvertDtoRs>> findByCity(@PathVariable String city) {
        return ResponseEntity.ok(advertService.findByApartmentCity(city));
    }
}

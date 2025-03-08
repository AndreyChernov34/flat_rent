package com.javaacademy.org.flat_rent.contoller;

import com.javaacademy.org.flat_rent.dto.ApartmentDto;
import com.javaacademy.org.flat_rent.service.ApartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apartment")
@Tag(name = "ApartmentController", description = "Контроллер для работы с помещениями")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PostMapping
    @Operation(summary = "Создание нового помещения")
    public ResponseEntity<ApartmentDto> save(@RequestBody ApartmentDto apartmentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(apartmentService.save(apartmentDto));
    }


}

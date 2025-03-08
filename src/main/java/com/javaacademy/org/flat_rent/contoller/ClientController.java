package com.javaacademy.org.flat_rent.contoller;

import com.javaacademy.org.flat_rent.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@CrossOrigin
@Tag(name = "ClientController", description = "Контроллер для работы с клиентами")
public class ClientController {
    private final ClientService clientService;

    @DeleteMapping("{id}")
    @Operation(summary = "удаление клиента по Id")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        clientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

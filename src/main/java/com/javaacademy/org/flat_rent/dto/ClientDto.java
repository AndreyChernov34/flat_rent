package com.javaacademy.org.flat_rent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ClientDto", description = "DTO для клиента")
public class ClientDto {
    private Integer id;
    private String name;
    private String email;
}

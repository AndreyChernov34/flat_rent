package com.javaacademy.org.flat_rent.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {

    private String name;
    private String email;
}

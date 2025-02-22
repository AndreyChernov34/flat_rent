package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.ClientDto;
import com.javaacademy.org.flat_rent.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClentMapper {
    ClientDto toDto(Client client);

    Client toEntity(ClientDto clientDto);
}

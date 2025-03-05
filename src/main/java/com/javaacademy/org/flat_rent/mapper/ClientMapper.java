package com.javaacademy.org.flat_rent.mapper;

import com.javaacademy.org.flat_rent.dto.ClientDto;
import com.javaacademy.org.flat_rent.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    ClientDto toDto(Client client);

    @Mapping(target = "bookingList", ignore = true)
    Client toEntity(ClientDto clientDto);
}

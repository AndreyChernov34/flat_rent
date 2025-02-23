package com.javaacademy.org.flat_rent.entity.service;

import com.javaacademy.org.flat_rent.dto.ClientDto;
import com.javaacademy.org.flat_rent.mapper.ClientMapper;
import com.javaacademy.org.flat_rent.repository.ClienttRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientMapper clientMapper;
    private final ClienttRepostory clienttRepostory;

    public ClientDto saveClient(ClientDto clientDto) {
        return clientMapper.toDto(
                clienttRepostory.save(clientMapper.toEntity(clientDto)));
    };
}

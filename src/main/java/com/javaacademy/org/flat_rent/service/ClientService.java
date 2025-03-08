package com.javaacademy.org.flat_rent.service;

import com.javaacademy.org.flat_rent.dto.ClientDto;
import com.javaacademy.org.flat_rent.entity.Client;
import com.javaacademy.org.flat_rent.exception.EntityNotFoundException;
import com.javaacademy.org.flat_rent.mapper.ClientMapper;
import com.javaacademy.org.flat_rent.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    public ClientDto save(ClientDto clientDto) {
        return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(clientDto)));
    }

    public Boolean existsById(Integer id) {
        return clientRepository.existsById(id);
    }

    public void deleteById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден с id = " + id));
        clientRepository.delete(client);
    }
}

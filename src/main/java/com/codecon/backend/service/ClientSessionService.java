package com.codecon.backend.service;

import com.codecon.backend.model.ClientSession;
import com.codecon.backend.repository.ClientSessionRepository;
import com.codecon.backend.shared.service.BaseService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ClientSessionService {

    ClientSessionRepository clientSessionRepository;

    public ClientSession save(ClientSession clientSession) {
        return clientSessionRepository.save(clientSession);
    }

    public Optional<ClientSession> findByToken(String token) {
        return clientSessionRepository.findById(token);
    }

}

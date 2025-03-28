package com.codecon.backend.repository;

import com.codecon.backend.model.ClientSession;
import com.codecon.backend.model.Example;
import com.codecon.backend.shared.repository.BaseRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientSessionRepository extends CrudRepository<ClientSession, String> {

}

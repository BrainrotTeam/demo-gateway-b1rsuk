package com.codecon.backend.service;

import com.codecon.backend.model.Example;
import com.codecon.backend.repository.ExampleRepository;
import com.codecon.backend.shared.service.BaseService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExampleService extends BaseService<Example> {

    public ExampleService(ExampleRepository exampleRepository) {
        super(exampleRepository);
    }

}

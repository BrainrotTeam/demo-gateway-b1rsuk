package com.codecon.backend.shared.service.exception;

import com.codecon.backend.shared.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityNotFoundException extends CustomException {

    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }


}

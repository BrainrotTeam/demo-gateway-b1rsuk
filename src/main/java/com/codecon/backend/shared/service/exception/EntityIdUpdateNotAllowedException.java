package com.codecon.backend.shared.service.exception;

import com.codecon.backend.shared.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EntityIdUpdateNotAllowedException extends CustomException {

    public EntityIdUpdateNotAllowedException() {
        this("Error: Creating an entity must not include an ID.");
    }

    public EntityIdUpdateNotAllowedException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
package com.codecon.backend.shared.service.exception;

import com.codecon.backend.shared.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EntityIdCannotBeNullException extends CustomException {

    public EntityIdCannotBeNullException() {
        this("Error: Update requires Id");
    }

    public EntityIdCannotBeNullException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}

package com.codecon.backend.exception;

import com.codecon.backend.model.Role;
import com.codecon.backend.model.dto.ClientDto;
import com.codecon.backend.shared.exception.CustomException;
import org.springframework.http.HttpStatus;
import java.util.Arrays;

public class UnAuthorizedException extends CustomException {

    public UnAuthorizedException(ClientDto clientDto, Role[] requiredRole) {
        this(String.format("Client with ID '%d' is attempting to access endpoint requiring one of the roles: %s, but their current role is '%s'.",
                clientDto.getId(),
                Arrays.toString(requiredRole),
                clientDto.getRole()));
    }

    public UnAuthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}

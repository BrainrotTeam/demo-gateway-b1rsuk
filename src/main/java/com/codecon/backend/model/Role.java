package com.codecon.backend.model;

import com.codecon.infrastructure.grpc.authentication.ClientPermission;
import lombok.Getter;

@Getter
public enum Role {
    MASTER(2),
    ADMIN(1),
    USER(0);

    private final int accessLevel;

    Role(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public static Role of(ClientPermission clientPermission) {
        return switch (clientPermission) {
            case USER -> Role.USER;
            case ADMIN -> Role.ADMIN;
            case MASTER -> Role.MASTER;
            case UNRECOGNIZED -> throw new RuntimeException("Unrecognized role");
        };
    }

}

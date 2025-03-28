package com.codecon.backend.model.dto;

import com.codecon.infrastructure.grpc.authentication.SignInRequest;
import com.codecon.infrastructure.grpc.authentication.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    String token;

    public static TokenDto of(TokenResponse tokenResponse) {
        String token = tokenResponse.getToken();
        return new TokenDto(token);
    }

}
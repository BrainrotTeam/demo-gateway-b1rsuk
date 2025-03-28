package com.codecon.backend.service;

import com.codecon.backend.model.dto.ClientDto;
import com.codecon.backend.model.dto.SignInDto;
import com.codecon.backend.model.dto.SignUpDto;
import com.codecon.backend.model.dto.TokenDto;
import com.codecon.infrastructure.grpc.authentication.*;
import com.codecon.infrastructure.grpc.authentication.ClientResponseDto;
import com.codecon.infrastructure.grpc.authentication.SignInRequest;
import com.codecon.infrastructure.grpc.authentication.SignUpRequest;
import com.codecon.infrastructure.grpc.authentication.TokenRequest;
import com.codecon.infrastructure.grpc.authentication.TokenResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GRPCAuthenticationService {

    AuthenticationServiceGrpc.AuthenticationServiceBlockingStub authenticationServiceStub;

    public GRPCAuthenticationService() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext() // todo: Настроить SSL
                .build();
        this.authenticationServiceStub = AuthenticationServiceGrpc.newBlockingStub(channel);
    }

    public TokenDto signUp(SignUpDto signUpDto) {
        SignUpRequest signUpRequest = signUpDto.toSignUpRequest();
        TokenResponse tokenResponse = authenticationServiceStub.signUp(signUpRequest);
        return TokenDto.of(tokenResponse);
    }

    public TokenDto signIn(SignInDto signInDto) {
        SignInRequest signInRequest = signInDto.toSignInRequest();
        TokenResponse tokenResponse = authenticationServiceStub.signIn(signInRequest);
        return TokenDto.of(tokenResponse);
    }

    public ClientDto getActualDataByToken(String bearerToken) {
        TokenRequest tokenRequest = TokenRequest
                .newBuilder()
                .setToken(bearerToken)
                .build();
        ClientResponseDto clientResponseDto = authenticationServiceStub.getActualDataByToken(tokenRequest);

        return ClientDto.convertToDto(clientResponseDto);
    }

}

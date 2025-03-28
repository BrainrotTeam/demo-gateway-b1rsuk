package com.codecon.backend.model.dto;

import com.codecon.infrastructure.grpc.authentication.SignInRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto implements Authentication {

    @NotBlank
    String password;

    @Email
    String email;

    public SignInRequest toSignInRequest() {
        return SignInRequest
                .newBuilder()
                .setEmail(email)
                .setPassword(password)
                .build();
    }

}

package com.codecon.backend.model.dto;

import com.codecon.infrastructure.grpc.authentication.SignUpRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.codecon.infrastructure.grpc.authentication.SignUpRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto implements Authentication {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String password;

    @Email
    String email;

    public SignUpRequest toSignUpRequest() {
        return SignUpRequest.newBuilder()
                .setEmail(email)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPassword(password)
                .build();
    }
}

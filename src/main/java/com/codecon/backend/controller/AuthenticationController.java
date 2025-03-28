package com.codecon.backend.controller;

import com.codecon.backend.model.dto.SignInDto;
import com.codecon.backend.model.dto.SignUpDto;
import com.codecon.backend.model.dto.TokenDto;
import com.codecon.backend.service.GRPCAuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    GRPCAuthenticationService grpcAuthenticationService;

    @PostMapping("/sign-up")
    public TokenDto signUp(@RequestBody SignUpDto signUpDto) {
        return grpcAuthenticationService.signUp(signUpDto);
    }

    @PostMapping("/sign-in")
    public TokenDto signIn(@RequestBody SignInDto signInDto) {
        return grpcAuthenticationService.signIn(signInDto);
    }


}

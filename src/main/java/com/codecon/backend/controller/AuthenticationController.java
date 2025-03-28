package com.codecon.backend.controller;

import com.codecon.backend.annotation.Require;
import com.codecon.backend.exception.UnAuthorizedException;
import com.codecon.backend.model.Role;
import com.codecon.backend.model.dto.SignInDto;
import com.codecon.backend.model.dto.SignUpDto;
import com.codecon.backend.model.dto.TokenDto;
import com.codecon.backend.service.GRPCAuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    GRPCAuthenticationService grpcAuthenticationService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpDto signUpDto) {
         grpcAuthenticationService.signUp(signUpDto);
    }

    @PostMapping("/sign-in")
    public TokenDto signIn(@RequestBody SignInDto signInDto) {
        return grpcAuthenticationService.signIn(signInDto);
    }

}

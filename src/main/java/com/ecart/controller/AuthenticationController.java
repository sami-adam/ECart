package com.ecart.controller;

import com.ecart.dto.JWTAuthenticationResponse;
import com.ecart.dto.RefreshTokenDTO;
import com.ecart.dto.SignInDTO;
import com.ecart.dto.SignUpDTO;
import com.ecart.entity.User;
import com.ecart.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody SignUpDTO signUpDTO){
        return ResponseEntity.ok(authenticationService.signUp(signUpDTO));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SignInDTO signInDTO){
        return ResponseEntity.ok(authenticationService.signIn(signInDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponse> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenDTO));
    }
}

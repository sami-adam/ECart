package com.ecart.service;

import com.ecart.dto.JWTAuthenticationResponse;
import com.ecart.dto.RefreshTokenDTO;
import com.ecart.dto.SignInDTO;
import com.ecart.dto.SignUpDTO;
import com.ecart.entity.User;

public interface AuthenticationService {
    User signUp(SignUpDTO signUpDTO);
    JWTAuthenticationResponse signIn(SignInDTO signInDTO);
    JWTAuthenticationResponse refreshToken(RefreshTokenDTO refreshTokenDTO);
}

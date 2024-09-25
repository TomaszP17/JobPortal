package com.jobportal.jobportal.services.auth;

import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import org.springframework.security.core.Authentication;

public interface AuthService {
    GenerateTokensDTO authenticate(Authentication authentication);
    GenerateTokensDTO refreshToken(String refreshToken);
    void logout(String refreshToken);
}

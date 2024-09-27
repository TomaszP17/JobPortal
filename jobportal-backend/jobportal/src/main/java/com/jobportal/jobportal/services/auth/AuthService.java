package com.jobportal.jobportal.services.auth;

import com.jobportal.jobportal.dtos.auth.CookiesTokensDTO;
import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import org.springframework.security.core.Authentication;

public interface AuthService {
    CookiesTokensDTO authenticate(Authentication authentication);
    CookiesTokensDTO refreshToken(String refreshToken);
    void logout(String refreshToken);
}

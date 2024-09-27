package com.jobportal.jobportal.services.auth;

import com.jobportal.jobportal.dtos.auth.CookiesTokensDTO;
import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import com.jobportal.jobportal.dtos.auth.LoginRequestDTO;
import org.springframework.security.core.Authentication;

public interface AuthService {
    CookiesTokensDTO authenticate(LoginRequestDTO loginRequestDTO);
    CookiesTokensDTO refreshToken(String refreshToken);
    void logout(String refreshToken);
}

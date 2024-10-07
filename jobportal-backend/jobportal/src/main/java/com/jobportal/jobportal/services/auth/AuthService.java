package com.jobportal.jobportal.services.auth;

import com.jobportal.jobportal.dtos.auth.CookiesTokensDTO;
import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import com.jobportal.jobportal.dtos.auth.LoginRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {
    CookiesTokensDTO authenticate(LoginRequestDTO loginRequestDTO);
    CookiesTokensDTO refreshToken(HttpServletRequest request);
    void logout(String refreshToken);
}

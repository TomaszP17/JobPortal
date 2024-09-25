package com.jobportal.jobportal.services.token;

import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import org.springframework.security.core.Authentication;

public interface TokenService {
    GenerateTokensDTO generateToken(Authentication authentication);
}

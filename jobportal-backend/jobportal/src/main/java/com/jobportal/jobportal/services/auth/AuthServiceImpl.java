package com.jobportal.jobportal.services.auth;

import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import com.jobportal.jobportal.entities.RefreshToken;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.repositories.RefreshTokenRepository;
import com.jobportal.jobportal.services.token.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthServiceImpl(TokenService tokenService, RefreshTokenRepository refreshTokenRepository) {
        this.tokenService = tokenService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public GenerateTokensDTO authenticate(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    @Override
    public GenerateTokensDTO refreshToken(String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenValue)
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token")); // create exception here

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new IllegalArgumentException("Refresh token expired");
        }

        User user = refreshToken.getUser();

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user.getEmail(), null);

       GenerateTokensDTO tokens = tokenService.generateToken(authentication);

        refreshTokenRepository.delete(refreshToken);

        return tokens;
    }

    @Override
    public void logout(String refreshTokenValue) {
        Optional<RefreshToken> byToken = refreshTokenRepository.findByToken(refreshTokenValue);
        System.out.println("BYTOKEN LOGOUT: " + byToken);

        refreshTokenRepository.findByToken(refreshTokenValue).ifPresent(refreshTokenRepository::delete);
    }

}

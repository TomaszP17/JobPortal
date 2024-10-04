package com.jobportal.jobportal.services.auth;

import com.jobportal.jobportal.dtos.auth.CookiesTokensDTO;
import com.jobportal.jobportal.dtos.auth.LoginRequestDTO;
import com.jobportal.jobportal.entities.RefreshToken;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.helpers.JwtCookiesHelpers;
import com.jobportal.jobportal.repositories.RefreshTokenRepository;
import com.jobportal.jobportal.services.token.TokenService;
import com.jobportal.jobportal.services.user.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtCookiesHelpers jwtCookiesHelpers;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(TokenService tokenService, RefreshTokenRepository refreshTokenRepository, JwtCookiesHelpers jwtCookiesHelpers, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtCookiesHelpers = jwtCookiesHelpers;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public CookiesTokensDTO authenticate(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password())
        );

        return jwtCookiesHelpers.createAuthCookies(tokenService.generateToken(authentication));
    }

    @Override
    public CookiesTokensDTO refreshToken(HttpServletRequest request) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(jwtCookiesHelpers.getTokenFromCookie(request, "refreshToken"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new IllegalArgumentException("Refresh token expired"); // change exception
        }

        User user = refreshToken.getUser();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

       CookiesTokensDTO tokens = jwtCookiesHelpers.createAuthCookies(tokenService.generateToken(authentication));

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

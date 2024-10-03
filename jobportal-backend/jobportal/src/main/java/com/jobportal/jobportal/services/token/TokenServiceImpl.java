package com.jobportal.jobportal.services.token;

import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import com.jobportal.jobportal.entities.RefreshToken;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.helpers.JwtHelpers;
import com.jobportal.jobportal.repositories.RefreshTokenRepository;
import com.jobportal.jobportal.repositories.UserRepository;
import com.jobportal.jobportal.services.user.CustomUserDetailsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService{

    private final JwtHelpers jwtHelpers;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    public TokenServiceImpl(JwtHelpers jwtHelpers, UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.jwtHelpers = jwtHelpers;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public GenerateTokensDTO generateToken(Authentication authentication) {
        Instant now = Instant.now();
        System.out.println(authentication);
        System.out.println("auth-role: " + authentication.getAuthorities());
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new UserDoesNotExistException("User with email " + authentication.getName() + " does not exist"));


        String accessToken =  jwtHelpers.generateJwtToken(authentication.getName(), scope);
        String refreshToken = UUID.randomUUID().toString();

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .token(refreshToken)
                .user(user)
                .expiryDate(now.plus(14, ChronoUnit.DAYS))
                .build();

        refreshTokenRepository.save(refreshTokenEntity);

        return new GenerateTokensDTO(accessToken, refreshToken);
    }

}

package com.jobportal.jobportal.services.token;

import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import com.jobportal.jobportal.entities.RefreshToken;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.repositories.RefreshTokenRepository;
import com.jobportal.jobportal.repositories.UserRepository;
import com.jobportal.jobportal.services.user.CustomUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService{

    private final JwtEncoder encoder;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailsService customUserDetailsService;

    public TokenServiceImpl(JwtEncoder encoder, UserRepository userRepository, RefreshTokenRepository refreshTokenRepository, CustomUserDetailsService customUserDetailsService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    public GenerateTokensDTO generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        String accessToken =  this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        String refreshToken = UUID.randomUUID().toString();

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .token(refreshToken)
                .user(userRepository
                        .findByEmail(authentication.getName())
                        .orElseThrow(() -> new UserDoesNotExistException("User with that email" + authentication.getName() + " does not exists")))
                .expiryDate(now.plus(14, ChronoUnit.DAYS))
                .build();

        refreshTokenRepository.save(refreshTokenEntity);

        return new GenerateTokensDTO(accessToken, refreshToken);
    }

}

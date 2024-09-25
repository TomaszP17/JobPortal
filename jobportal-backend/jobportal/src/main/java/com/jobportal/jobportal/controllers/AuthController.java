package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import com.jobportal.jobportal.dtos.auth.RefreshTokenDTO;
import com.jobportal.jobportal.services.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<GenerateTokensDTO> login(Authentication authentication) {
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        GenerateTokensDTO tokens = authService.authenticate(authentication);
        LOG.debug("Token granted: {}", tokens);
        return new ResponseEntity<>(tokens, HttpStatus.CREATED);
    }

    //todo responseentity<>
    @PostMapping("/refresh")
    public ResponseEntity<GenerateTokensDTO> refresh(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        LOG.debug("Refresh token requested");
        GenerateTokensDTO tokens = authService.refreshToken(refreshTokenDTO.refreshToken());
        LOG.debug("Tokens refreshed");
        return new ResponseEntity<>(tokens, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        LOG.debug("Logout requested");
        authService.logout(refreshTokenDTO.refreshToken());
        LOG.debug("User logged out");
    }

//    @PostMapping("/oauth2/authorize/{provider}")
//    public ResponseEntity<GenerateTokensDTO> authorize(@PathVariable String provider) {
//
//    }

}

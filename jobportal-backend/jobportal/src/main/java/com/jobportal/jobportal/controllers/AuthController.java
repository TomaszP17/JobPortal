package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.auth.CookiesTokensDTO;
import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import com.jobportal.jobportal.dtos.auth.LoginRequestDTO;
import com.jobportal.jobportal.dtos.auth.RefreshTokenDTO;
import com.jobportal.jobportal.services.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<String> login(HttpServletResponse httpServletResponse, @RequestBody LoginRequestDTO loginRequestDTO) {
        CookiesTokensDTO cookiesTokensDTO = authService.authenticate(loginRequestDTO);
        httpServletResponse.addCookie(cookiesTokensDTO.accessTokenCookie());
        httpServletResponse.addCookie(cookiesTokensDTO.refreshTokenCookie());
        return new ResponseEntity<>("logged in successfully", HttpStatus.CREATED);
    }

    //todo responseentity<>
    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        CookiesTokensDTO CookiesTokensDTO = authService.refreshToken(httpServletRequest);
        httpServletResponse.addCookie(CookiesTokensDTO.accessTokenCookie());
        httpServletResponse.addCookie(CookiesTokensDTO.refreshTokenCookie());

        return new ResponseEntity<>("user's tokens have been refreshed", HttpStatus.CREATED);
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

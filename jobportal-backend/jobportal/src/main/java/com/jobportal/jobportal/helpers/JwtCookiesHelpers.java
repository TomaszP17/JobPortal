package com.jobportal.jobportal.helpers;

import com.jobportal.jobportal.dtos.auth.CookiesTokensDTO;
import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtCookiesHelpers {

    @Value("${jwt.access-token.expiry}")
    private int accessTokenExpiry;

    @Value("${jwt.refresh-token.expiry}")
    private int refreshTokenExpiry;

    public CookiesTokensDTO createAuthCookies(GenerateTokensDTO generateTokensDTO) {
        Cookie accessTokenCookie = createCookie("accessToken", generateTokensDTO.accessToken(), accessTokenExpiry);
        Cookie refreshTokenCookie = createCookie("refreshToken", generateTokensDTO.refreshToken(), refreshTokenExpiry);

        return new CookiesTokensDTO(accessTokenCookie, refreshTokenCookie);
    }

    private static Cookie createCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        cookie.setAttribute("SameSite", "Strict");
        return cookie;
    }
}


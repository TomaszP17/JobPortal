package com.jobportal.jobportal.dtos.auth;

import jakarta.servlet.http.Cookie;

public record CookiesTokensDTO(Cookie accessTokenCookie, Cookie refreshTokenCookie) {
}

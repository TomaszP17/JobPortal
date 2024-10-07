package com.jobportal.jobportal.configs.filters;

import com.jobportal.jobportal.helpers.JwtCookiesHelpers;
import com.jobportal.jobportal.helpers.JwtHelpers;
import com.jobportal.jobportal.services.user.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtHelpers jwtHelpers;
    private final CustomUserDetailsService userDetailsService;

    private final JwtCookiesHelpers jwtCookiesHelpers;


    public JwtAuthenticationFilter(JwtHelpers jwtHelpers, CustomUserDetailsService userDetailsService, JwtCookiesHelpers jwtCookiesHelpers) {
        this.jwtHelpers = jwtHelpers;
        this.userDetailsService = userDetailsService;
        this.jwtCookiesHelpers = jwtCookiesHelpers;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String accessToken = jwtCookiesHelpers.getTokenFromCookie(request, "accessToken");

            if (StringUtils.hasText(accessToken) && jwtHelpers.validateJwtToken(accessToken)) {
                String username = jwtHelpers.getEmailFromJwtToken(accessToken);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Nie można ustawić autentykacji użytkownika: {}", e);
        }

        filterChain.doFilter(request, response);
    }
}

package com.jobportal.jobportal.configs.filters;

import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.helpers.JwtCookiesHelpers;
import com.jobportal.jobportal.helpers.JwtHelpers;
import com.jobportal.jobportal.repositories.UserRepository;
import com.jobportal.jobportal.services.user.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtHelpers jwtHelpers;
    private final UserRepository userRepository;
    private final JwtCookiesHelpers jwtCookiesHelpers;


    public JwtAuthenticationFilter(JwtHelpers jwtHelpers, UserRepository userRepository, JwtCookiesHelpers jwtCookiesHelpers) {
        this.jwtHelpers = jwtHelpers;
        this.userRepository = userRepository;
        this.jwtCookiesHelpers = jwtCookiesHelpers;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String accessToken = jwtCookiesHelpers.getTokenFromCookie(request, "accessToken");

            if (StringUtils.hasText(accessToken) && jwtHelpers.validateJwtToken(accessToken)) {
                String email = jwtHelpers.getEmailFromJwtToken(accessToken);

                User user = userRepository.findByEmail(email).orElseThrow(() -> new UserDoesNotExistException("User with email: " +
                        email + " has not been found."));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user.getEmail(), null, user.getUserAuthority()
                                .stream()
                                .map(ua -> new SimpleGrantedAuthority(ua.getAuthority().getName()))
                                .toList());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Nie można ustawić autentykacji użytkownika: {}", e);
        }

        filterChain.doFilter(request, response);
    }
}

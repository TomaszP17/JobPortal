package com.jobportal.jobportal.handlers;

import com.jobportal.jobportal.dtos.auth.CookiesTokensDTO;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.helpers.JwtCookiesHelpers;
import com.jobportal.jobportal.repositories.UserRepository;
import com.jobportal.jobportal.services.token.TokenService;
import com.jobportal.jobportal.services.user.CustomUserDetailsService;
import com.jobportal.jobportal.services.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final JwtCookiesHelpers jwtCookiesHelpers;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;

    @Value("${jobportal.frontend.url}")
    private String frontendUrl;

    public OAuth2AuthenticationSuccessHandler(
            @Lazy TokenService tokenService,
            JwtCookiesHelpers jwtCookiesHelpers,
            CustomUserDetailsService customUserDetailsService,
            UserRepository userRepository) {
        this.tokenService = tokenService;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtCookiesHelpers = jwtCookiesHelpers;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        handleAuthenticationForUserType(response, email, name, userRepository.findByEmail(email));
    }

    private void handleAuthenticationForUserType(HttpServletResponse response, String email, String name,
                                                 Optional<? extends User> userOptional) throws IOException {

        if (userOptional.isPresent()) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            setCookiesAndRedirect(response, authentication);
        } else {
            redirectToFrontend(response, true, name, email);
        }
    }


    private void setCookiesAndRedirect(HttpServletResponse response, Authentication authentication) throws IOException {
        CookiesTokensDTO tokens = jwtCookiesHelpers.createAuthCookies(tokenService.generateToken(authentication));
        response.addCookie(tokens.accessTokenCookie());
        response.addCookie(tokens.refreshTokenCookie());

        redirectToFrontend(response, false, null, null);
    }

    private void redirectToFrontend(HttpServletResponse response, boolean requiresAdditionalInfo, String name, String email) throws IOException {
        String redirectUrl = "";

        if (requiresAdditionalInfo) {
            name = name.strip().replace(" ", "+");
            email = email.strip();
            redirectUrl = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/redirect")
                    .queryParam("requiresAdditionalInfo", requiresAdditionalInfo)
                    .queryParam("name", name)
                    .queryParam("email", email)
                    .build().toUriString();
        } else {
            redirectUrl = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/redirect")
                    .queryParam("requiresAdditionalInfo", requiresAdditionalInfo)
                    .build().toUriString();
        }


        response.sendRedirect(redirectUrl);
    }
}

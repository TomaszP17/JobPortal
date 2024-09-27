package com.jobportal.jobportal.handlers;

import com.jobportal.jobportal.dtos.auth.CookiesTokensDTO;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.Company;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.helpers.JwtCookiesHelpers;
import com.jobportal.jobportal.repositories.CandidateRepository;
import com.jobportal.jobportal.repositories.CompanyRepository;
import com.jobportal.jobportal.services.candidate.CandidateService;
import com.jobportal.jobportal.services.token.TokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;
    private final CandidateService candidateService;
    private final JwtCookiesHelpers jwtCookiesHelpers;

    @Value("${jobportal.frontend.url}")
    private String frontendUrl;

    public OAuth2AuthenticationSuccessHandler(
            @Lazy TokenService tokenService,
            CandidateRepository candidateRepository,
            CompanyRepository companyRepository,
            @Lazy CandidateService candidateService,
            JwtCookiesHelpers jwtCookiesHelpers) {
        this.tokenService = tokenService;
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
        this.candidateService = candidateService;
        this.jwtCookiesHelpers = jwtCookiesHelpers;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        String userType = "candidate";

        if ("candidate".equals(userType)) {
            handleAuthenticationForUserType(response, email, name, "ROLE_CANDIDATE", candidateRepository.findByEmail(email), true);
        } else if ("company".equals(userType)) {
            handleAuthenticationForUserType(response, email, name, "ROLE_COMPANY", companyRepository.findByEmail(email), false);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user type");
        }
    }

    private void handleAuthenticationForUserType(HttpServletResponse response, String email, String name, String role,
                                                 Optional<? extends User> userOptional, boolean isCandidate) throws IOException {

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getEmail(), null, List.of(new SimpleGrantedAuthority(role)));
            setCookiesAndRedirect(response, newAuth, false);
        } else {
            User newUser = isCandidate ? candidateService.createCandidateFromOAuth(email) : createCompany(email, name);
            Authentication newAuth = new UsernamePasswordAuthenticationToken(newUser.getEmail(), null, List.of(new SimpleGrantedAuthority(role)));
            setCookiesAndRedirect(response, newAuth, true);
        }
    }

    private Company createCompany(String email, String name) {
        Company newCompany = new Company();
        newCompany.setEmail(email);
        newCompany.setName(name);
        newCompany.setIsCompleted(false);
        companyRepository.save(newCompany);
        return newCompany;
    }

    private void setCookiesAndRedirect(HttpServletResponse response, Authentication authentication, boolean requiresAdditionalInfo) throws IOException {
        CookiesTokensDTO tokens = jwtCookiesHelpers.createAuthCookies(tokenService.generateToken(authentication));
        response.addCookie(tokens.accessTokenCookie());
        response.addCookie(tokens.refreshTokenCookie());

        redirectToFrontend(response, requiresAdditionalInfo);
    }

    private void redirectToFrontend(HttpServletResponse response, boolean requiresAdditionalInfo) throws IOException {
        String redirectUrl = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/redirect")
                .queryParam("requiresAdditionalInfo", requiresAdditionalInfo)
                .build().toUriString();

        response.sendRedirect(redirectUrl);
    }
}

package com.jobportal.jobportal.handlers;

import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.Company;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.repositories.CandidateRepository;
import com.jobportal.jobportal.repositories.CompanyRepository;
import com.jobportal.jobportal.repositories.UserRepository;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;
    private final CandidateService candidateService;

    @Value("${jobportal.frontend.url}")
    private String frontendUrl;

    public OAuth2AuthenticationSuccessHandler(
            @Lazy TokenService tokenService,
            CandidateRepository candidateRepository,
            CompanyRepository companyRepository, @Lazy CandidateService candidateService) {
        this.tokenService = tokenService;
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
        this.candidateService = candidateService;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        String userType = "candidate";

        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if ("candidate".equals(userType)) {
           grantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_CANDIDATE"));
            Optional<Candidate> candidateOptional = candidateRepository.findByEmail(email);

            if (candidateOptional.isPresent()) {
                Candidate candidate = candidateOptional.get();
                Authentication newAuth = new UsernamePasswordAuthenticationToken(
                        candidate.getEmail(), null, grantedAuthorities);

                System.out.println(newAuth);
                GenerateTokensDTO tokens = tokenService.generateToken(newAuth);

                redirectToFrontend(response, tokens, false);

            } else {
                Candidate newCandidate = candidateService.createCandidateFromOAuth(email);

                Authentication newAuth = new UsernamePasswordAuthenticationToken(
                        newCandidate.getEmail(), null, grantedAuthorities);

                GenerateTokensDTO tokens = tokenService.generateToken(newAuth);

                redirectToFrontend(response, tokens, true);
            }
        } else if ("company".equals(userType)) {
            grantedAuthorities = List.of(new SimpleGrantedAuthority("ROLE_COMPANY"));
            Optional<Company> companyOptional = companyRepository.findByEmail(email);

            if (companyOptional.isPresent()) {
                Company company = companyOptional.get();
                Authentication newAuth = new UsernamePasswordAuthenticationToken(
                        company.getEmail(), null, grantedAuthorities);

                GenerateTokensDTO tokens = tokenService.generateToken(newAuth);

                redirectToFrontend(response, tokens, false);
            } else {
                Company newCompany = new Company();
                newCompany.setEmail(email);
                newCompany.setName(name);
                newCompany.setIsCompleted(false);
                companyRepository.save(newCompany);

                Authentication newAuth = new UsernamePasswordAuthenticationToken(
                        newCompany.getEmail(), null, grantedAuthorities);

                GenerateTokensDTO tokens = tokenService.generateToken(newAuth);

                redirectToFrontend(response, tokens, true);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user type");
        }
    }

    private void redirectToFrontend(HttpServletResponse response, GenerateTokensDTO tokens, boolean requiresAdditionalInfo) throws IOException {
        String redirectUrl = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/redirect")
                .queryParam("accessToken", tokens.accessToken())
                .queryParam("refreshToken", tokens.refreshToken())
                .queryParam("requiresAdditionalInfo", requiresAdditionalInfo)
                .build().toUriString();

        response.sendRedirect(redirectUrl);
    }
}

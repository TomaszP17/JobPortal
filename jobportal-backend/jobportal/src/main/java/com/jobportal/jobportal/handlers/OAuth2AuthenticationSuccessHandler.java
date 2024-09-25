package com.jobportal.jobportal.handlers;

import com.jobportal.jobportal.dtos.auth.GenerateTokensDTO;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.entities.user.Company;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.repositories.CandidateRepository;
import com.jobportal.jobportal.repositories.CompanyRepository;
import com.jobportal.jobportal.repositories.UserRepository;
import com.jobportal.jobportal.services.token.TokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;

    @Value("${jobportal.frontend.url}")
    private String frontendUrl;

    public OAuth2AuthenticationSuccessHandler(
            @Lazy TokenService tokenService,
            CandidateRepository candidateRepository,
            CompanyRepository companyRepository) {
        this.tokenService = tokenService;
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        String state = request.getParameter("state");
        String userType = "candidate";


        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        if ("candidate".equals(userType)) {
            Optional<Candidate> candidateOptional = candidateRepository.findByEmail(email);

            if (candidateOptional.isPresent()) {
                Candidate candidate = candidateOptional.get();
                Authentication newAuth = new UsernamePasswordAuthenticationToken(
                        candidate.getEmail(), null);
                GenerateTokensDTO tokens = tokenService.generateToken(newAuth);

                redirectToFrontend(response, tokens, false, userType);

            } else {
                Candidate newCandidate = new Candidate();
                newCandidate.setEmail(email);
                newCandidate.setFirstName(name);
                newCandidate.setIsCompleted(false);
                candidateRepository.save(newCandidate);

                Authentication newAuth = new UsernamePasswordAuthenticationToken(
                        newCandidate.getEmail(), null);

                GenerateTokensDTO tokens = tokenService.generateToken(newAuth);

                redirectToFrontend(response, tokens, true, userType);
            }
        } else if ("company".equals(userType)) {
            Optional<Company> companyOptional = companyRepository.findByEmail(email);

            if (companyOptional.isPresent()) {
                Company company = companyOptional.get();
                Authentication newAuth = new UsernamePasswordAuthenticationToken(
                        company.getEmail(), null);

                GenerateTokensDTO tokens = tokenService.generateToken(newAuth);

                redirectToFrontend(response, tokens, false, userType);
            } else {
                Company newCompany = new Company();
                newCompany.setEmail(email);
                newCompany.setName(name);
                newCompany.setIsCompleted(false);
                companyRepository.save(newCompany);

                Authentication newAuth = new UsernamePasswordAuthenticationToken(
                        newCompany.getEmail(), null);

                GenerateTokensDTO tokens = tokenService.generateToken(newAuth);

                redirectToFrontend(response, tokens, true, userType);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user type");
        }
    }

    private void redirectToFrontend(HttpServletResponse response, GenerateTokensDTO tokens, boolean requiresAdditionalInfo, String userType) throws IOException {
        String redirectUrl = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/redirect")
                .queryParam("accessToken", tokens.accessToken())
                .queryParam("refreshToken", tokens.refreshToken())
                .queryParam("requiresAdditionalInfo", requiresAdditionalInfo)
                .queryParam("userType", userType)
                .build().toUriString();

        response.sendRedirect(redirectUrl);
    }
}

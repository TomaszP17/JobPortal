package com.jobportal.jobportal.services.application;

import com.jobportal.jobportal.dtos.application.CreateApplicationRequestDTO;
import com.jobportal.jobportal.entities.Application;
import com.jobportal.jobportal.entities.offer.Offer;
import com.jobportal.jobportal.entities.user.Candidate;
import com.jobportal.jobportal.exceptions.OfferDoesNotExistsException;
import com.jobportal.jobportal.exceptions.user.UserDoesNotExistException;
import com.jobportal.jobportal.repositories.ApplicationRepository;
import com.jobportal.jobportal.repositories.CandidateRepository;
import com.jobportal.jobportal.repositories.OfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationServiceImpl implements ApplicationService{

    private final CandidateRepository candidateRepository;
    private final ApplicationRepository applicationRepository;
    private final OfferRepository offerRepository;

    public ApplicationServiceImpl(CandidateRepository candidateRepository, ApplicationRepository applicationRepository, OfferRepository offerRepository) {
        this.candidateRepository = candidateRepository;
        this.applicationRepository = applicationRepository;
        this.offerRepository = offerRepository;
    }

    @Transactional
    @Override
    public String applyToOffer(CreateApplicationRequestDTO createApplicationRequestDTO) {
        Candidate candidate = candidateRepository
                .findById(createApplicationRequestDTO.candidateId())
                .orElseThrow(() -> new UserDoesNotExistException("Candidate with id: " + createApplicationRequestDTO.candidateId() + " does not exist"));

        Offer offer = offerRepository
                .findById(createApplicationRequestDTO.offerId())
                .orElseThrow(() -> new OfferDoesNotExistsException("Offer with id: " + createApplicationRequestDTO.offerId() + " does not exist"));

        Application application = Application.builder()
                .offer(offer)
                .candidate(candidate)
                .pdf(createApplicationRequestDTO.pdf())
                .build();

        applicationRepository.save(application);

        return "User application created successfully";
    }
}

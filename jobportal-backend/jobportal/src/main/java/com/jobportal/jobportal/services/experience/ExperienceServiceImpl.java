package com.jobportal.jobportal.services.experience;

import com.jobportal.jobportal.dtos.experience.ExperienceCreateRequestDTO;
import com.jobportal.jobportal.dtos.experience.ExperienceResponseDTO;
import com.jobportal.jobportal.entities.offer.Experience;
import com.jobportal.jobportal.exceptions.offer.ExperienceDoesNotExistsException;
import com.jobportal.jobportal.mappers.OfferMapper;
import com.jobportal.jobportal.repositories.ExperienceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService{
    private final ExperienceRepository experienceRepository;
    private final OfferMapper offerMapper;
    public ExperienceServiceImpl(ExperienceRepository experienceRepository, OfferMapper offerMapper) {
        this.experienceRepository = experienceRepository;
        this.offerMapper = offerMapper;
    }
    @Override
    public List<ExperienceResponseDTO> getAllExperiences() {
        return experienceRepository
                .findAll()
                .stream()
                .map(offerMapper::toExperienceResponseDTOFromExperience)
                .toList();
    }
    @Override
    public ExperienceResponseDTO getExperience(long experienceId) {

        Experience experience = experienceRepository
                .findById(experienceId)
                .orElseThrow(() -> new ExperienceDoesNotExistsException("Experience with that id: " + experienceId + " does not exists"));
        return offerMapper.toExperienceResponseDTOFromExperience(experience);
    }
    @Override
    @Transactional
    public void addExperience(ExperienceCreateRequestDTO requestDTO) {
        Experience experience = offerMapper.toExperienceFromCreateRequestDTO(requestDTO);
        experienceRepository.save(experience);
    }
    @Override
    @Transactional
    public void deleteExperience(long experienceId) {
        Experience experience = experienceRepository
                .findById(experienceId)
                .orElseThrow(() -> new ExperienceDoesNotExistsException("Experience with that id: " + experienceId + " does not exists"));
        experienceRepository.delete(experience);
    }
}

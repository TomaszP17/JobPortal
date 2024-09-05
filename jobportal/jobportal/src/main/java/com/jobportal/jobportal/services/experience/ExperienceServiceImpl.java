package com.jobportal.jobportal.services.experience;

import com.jobportal.jobportal.dtos.experience.ExperienceResponseDTO;
import com.jobportal.jobportal.entities.offer.Experience;
import com.jobportal.jobportal.exceptions.ExperienceDoesNotExistsException;
import com.jobportal.jobportal.repositories.ExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService{

    private final ExperienceRepository experienceRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<ExperienceResponseDTO> getAllExperiences() {
        return experienceRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ExperienceResponseDTO getExperience(long experienceId) {

        Experience experience = experienceRepository
                .findById(experienceId)
                .orElseThrow(() -> new ExperienceDoesNotExistsException("Experience with that id: " + experienceId + " does not exists"));
        return convertToDTO(experience);
    }

    private ExperienceResponseDTO convertToDTO(Experience experience){
        return new ExperienceResponseDTO(
                experience.getId(),
                experience.getName()
        );
    }
}

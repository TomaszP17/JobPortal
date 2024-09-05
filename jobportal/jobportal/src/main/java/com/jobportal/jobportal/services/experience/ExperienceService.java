package com.jobportal.jobportal.services.experience;

import com.jobportal.jobportal.dtos.experience.ExperienceCreateRequestDTO;
import com.jobportal.jobportal.dtos.experience.ExperienceResponseDTO;

import java.util.List;

public interface ExperienceService {

    List<ExperienceResponseDTO> getAllExperiences();

    ExperienceResponseDTO getExperience(long experienceId);

    void addExperience(ExperienceCreateRequestDTO requestDTO);

    void deleteExperience(long experienceId);
}

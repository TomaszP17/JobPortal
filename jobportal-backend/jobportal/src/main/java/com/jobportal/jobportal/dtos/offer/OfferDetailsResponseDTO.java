package com.jobportal.jobportal.dtos.offer;

import com.jobportal.jobportal.dtos.company.CompanyResponseDTO;
import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeResponseDTO;
import com.jobportal.jobportal.dtos.experience.ExperienceResponseDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationResponseDTO;
import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO;
import com.jobportal.jobportal.dtos.worktype.WorkTypeResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record OfferDetailsResponseDTO(
        /*Long id,
        String title,
        LocalDateTime expiryDate,
        Integer salaryMin,
        Integer salaryMax,
        String description,*/
        OfferResponseDTO offerResponseDTO,
        List<TechnologyResponseDTO> technologyResponseDTOList,
        List<WorkTypeResponseDTO> workTypeResponseDTOList,
        List<ExperienceResponseDTO> experienceResponseDTOList,
        List<EmploymentTypeResponseDTO> employmentTypeResponseDTOList,
        LocalizationResponseDTO localizationResponseDTO,
        CompanyResponseDTO companyResponseDTO
) {
}

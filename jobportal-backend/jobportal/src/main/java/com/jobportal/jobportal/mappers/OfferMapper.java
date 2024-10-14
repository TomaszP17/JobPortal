package com.jobportal.jobportal.mappers;

import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeResponseDTO;
import com.jobportal.jobportal.dtos.experience.ExperienceCreateRequestDTO;
import com.jobportal.jobportal.dtos.experience.ExperienceResponseDTO;
import com.jobportal.jobportal.dtos.offer.OfferCreateRequestDTO;
import com.jobportal.jobportal.dtos.offer.OfferMarkerResponseDTO;
import com.jobportal.jobportal.dtos.offer.OfferResponseDTO;
import com.jobportal.jobportal.dtos.technology.TechnologyCreateRequestDTO;
import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO;
import com.jobportal.jobportal.dtos.worktype.WorkTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.worktype.WorkTypeResponseDTO;
import com.jobportal.jobportal.entities.offer.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    Offer toOfferFromCreateRequest(OfferCreateRequestDTO createOfferRequestDTO);
    OfferCreateRequestDTO toCreateResponseFromOffer(Offer offer);
    Offer toOfferFromResponseDTO(OfferResponseDTO responseDTO);
    OfferResponseDTO toOfferResponseFromOffer(Offer offer);

    OfferMarkerResponseDTO toOfferMarkerResponseFromOffer(Offer offer);

    EmploymentType toEmploymentTypeFromResponseDTO(EmploymentTypeResponseDTO responseDTO);
    EmploymentTypeResponseDTO toEmploymentTypeResponseDTOFromEmploymentType(EmploymentType employmentType);
    EmploymentType toEmploymentTypeFromCreateRequestDTO(EmploymentTypeCreateRequestDTO createRequestDTO);
    EmploymentTypeCreateRequestDTO toEmploymentTypeCreateRequestDTOFromEmploymentType(EmploymentType employmentType);

    Experience toExperienceFromResponseDTO(ExperienceResponseDTO responseDTO);
    ExperienceResponseDTO toExperienceResponseDTOFromExperience(Experience experience);
    Experience toExperienceFromCreateRequestDTO(ExperienceCreateRequestDTO createRequestDTO);
    ExperienceCreateRequestDTO toExperienceCreateRequestDTOFromExperience(Experience experience);

    Technology toTechnologyFromResponseDTO(TechnologyResponseDTO responseDTO);
    TechnologyResponseDTO toTechnologyResponseDTOFromTechnology(Technology technology);
    Technology toTechnologyFromCreateRequestDTO(TechnologyCreateRequestDTO createRequestDTO);
    TechnologyCreateRequestDTO toTechnologyCreateRequestDTOFromTechnology(Technology technology);

    WorkType toWorkTypeFromResponseDTO(WorkTypeResponseDTO responseDTO);
    WorkTypeResponseDTO toWorkTypeResponseDTOFromWorkType(WorkType workType);
    WorkType toWorkTypeFromCreateRequestDTO(WorkTypeCreateRequestDTO createRequestDTO);
    WorkTypeCreateRequestDTO toWorkTypeCreateRequestDTOFromWorkType(WorkType workType);
}

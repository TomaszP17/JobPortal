package com.jobportal.jobportal.mappers;

import com.jobportal.jobportal.dtos.localization.LocalizationResponseDTO;
import com.jobportal.jobportal.entities.Localization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalizationMapper {

    Localization toLocalizationFromResponseDTO(LocalizationResponseDTO responseDTO);
    LocalizationResponseDTO toLocalizationResponseDTOFromLocalization(Localization localization);

}

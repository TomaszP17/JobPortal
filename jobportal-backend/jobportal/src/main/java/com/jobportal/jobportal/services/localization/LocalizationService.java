package com.jobportal.jobportal.services.localization;

import com.jobportal.jobportal.dtos.localization.LocalizationCoordinatesResponseDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationCreateRequestDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationResponseDTO;
import com.jobportal.jobportal.entities.Localization;

import java.util.List;

public interface LocalizationService {

    Localization addLocalization(LocalizationCreateRequestDTO requestDTO);

    List<LocalizationResponseDTO> getAllLocalizations();

    List<LocalizationCoordinatesResponseDTO> getAllLocalizationsAndOfferIds();
}

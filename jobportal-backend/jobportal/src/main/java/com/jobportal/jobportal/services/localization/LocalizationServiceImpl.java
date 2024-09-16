package com.jobportal.jobportal.services.localization;

import com.jobportal.jobportal.dtos.geocoding.CoordinatesDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationCreateRequestDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationResponseDTO;
import com.jobportal.jobportal.entities.Localization;
import com.jobportal.jobportal.mappers.LocalizationMapper;
import com.jobportal.jobportal.repositories.LocalizationRepository;
import com.jobportal.jobportal.services.geocoding.GeocodingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizationServiceImpl implements LocalizationService{

    private final LocalizationRepository localizationRepository;

    private final GeocodingService geocodingService;
    private final LocalizationMapper localizationMapper;

    public LocalizationServiceImpl(LocalizationRepository localizationRepository, GeocodingService geocodingService, LocalizationMapper localizationMapper) {
        this.localizationRepository = localizationRepository;
        this.geocodingService = geocodingService;
        this.localizationMapper = localizationMapper;
    }

    @Override
    public Localization addLocalization(LocalizationCreateRequestDTO requestDTO) {
        String street = requestDTO.getStreet();
        Long buildingNumber = requestDTO.getBuildingNumber();
        String city = requestDTO.getCity();

        CoordinatesDTO coordinatesDTO = geocodingService.fetchCoordinates(street + " " + buildingNumber + " " + city);
        Double lat = coordinatesDTO.getLat();
        Double lng = coordinatesDTO.getLng();

        Localization localization = Localization.builder()
                .name(city)
                .lng(lng)
                .lat(lat)
                .build();

        localizationRepository.save(localization);
        return localization;
    }

    @Override
    public List<LocalizationResponseDTO> getAllLocalizations() {
        return localizationRepository
                .findAll()
                .stream()
                .map(localizationMapper::toLocalizationResponseDTOFromLocalization)
                .toList();
    }
}

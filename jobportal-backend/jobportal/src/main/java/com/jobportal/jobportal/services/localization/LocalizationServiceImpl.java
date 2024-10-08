package com.jobportal.jobportal.services.localization;

import com.jobportal.jobportal.dtos.geocoding.CoordinatesDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationCoordinatesResponseDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationCreateRequestDTO;
import com.jobportal.jobportal.dtos.localization.LocalizationResponseDTO;
import com.jobportal.jobportal.entities.Localization;
import com.jobportal.jobportal.mappers.LocalizationMapper;
import com.jobportal.jobportal.repositories.LocalizationRepository;
import com.jobportal.jobportal.services.geocoding.GeocodingService;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<LocalizationCoordinatesResponseDTO> getAllLocalizationsAndOfferIds() {
        List<Object[]> results = localizationRepository.findLocalizationIdLatLngAndOfferIds();
        Map<Long, LocalizationCoordinatesResponseDTO> dtoMap = new HashMap<>();

        for (Object[] row : results) {
            Long localizationId = ((Number) row[0]).longValue();
            Double lat = (Double) row[1];
            Double lng = (Double) row[2];
            Long offerId = row[3] != null ? ((Number) row[3]).longValue() : null;

            LocalizationCoordinatesResponseDTO dto = dtoMap.get(localizationId);

            if (dto == null) {
                dto = new LocalizationCoordinatesResponseDTO(lat, lng, new HashSet<>());
                dtoMap.put(localizationId, dto);
            }
            if (offerId != null) {
                dto.getOfferIds().add(offerId);
            }
        }

        return new ArrayList<>(dtoMap.values());
    }


}

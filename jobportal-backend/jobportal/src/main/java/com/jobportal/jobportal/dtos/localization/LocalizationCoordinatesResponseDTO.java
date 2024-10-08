package com.jobportal.jobportal.dtos.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizationCoordinatesResponseDTO{
    private Double lat;
    private Double lng;
    private Set<Long> offerIds;
}

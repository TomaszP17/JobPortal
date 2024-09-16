package com.jobportal.jobportal.dtos.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizationResponseDTO {

    private Long id;
    private String name;
    private Double lat;
    private Double lng;

}

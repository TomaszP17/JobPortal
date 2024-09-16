package com.jobportal.jobportal.dtos.geocoding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesDTO {

    private Double lat;
    private Double lng;
}

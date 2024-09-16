package com.jobportal.jobportal.dtos.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizationCreateRequestDTO {

    private String street;
    private Long buildingNumber;
    private String city;

}

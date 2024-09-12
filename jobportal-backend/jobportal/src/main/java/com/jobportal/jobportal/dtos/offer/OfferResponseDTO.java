package com.jobportal.jobportal.dtos.offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferResponseDTO {

    private Long id;

    private String title;

    private LocalDateTime expiryDate;

    private Integer salaryMin;

    private Integer salaryMax;

    private String description;

}

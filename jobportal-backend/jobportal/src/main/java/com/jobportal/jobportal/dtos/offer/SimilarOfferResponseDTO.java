package com.jobportal.jobportal.dtos.offer;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SimilarOfferResponseDTO {
        private Long id;
        private String title;
        private Timestamp expiryDate;
        private Integer salaryMin;
        private Integer salaryMax;
        private String description;
        private Double similarityScore;
}

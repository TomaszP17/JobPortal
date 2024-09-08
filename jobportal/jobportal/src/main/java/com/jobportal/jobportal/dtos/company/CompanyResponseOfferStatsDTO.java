package com.jobportal.jobportal.dtos.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseOfferStatsDTO {

    private String companyName;

    private Long offerCount;

    private Double averageMaxSalary;
}

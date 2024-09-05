package com.jobportal.jobportal.services.application;

import com.jobportal.jobportal.dtos.application.CreateApplicationRequestDTO;

public interface ApplicationService {
    String applyToOffer(CreateApplicationRequestDTO createApplicationRequestDTO);
}

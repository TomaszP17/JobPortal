package com.jobportal.jobportal.services.localization;

import com.jobportal.jobportal.repositories.LocalizationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalizationServiceImpl implements LocalizationService{

    private final LocalizationRepository localizationRepository;

    public LocalizationServiceImpl(LocalizationRepository localizationRepository) {
        this.localizationRepository = localizationRepository;
    }
}

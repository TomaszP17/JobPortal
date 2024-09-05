package com.jobportal.jobportal.services.technology;

import com.jobportal.jobportal.dtos.technology.TechnologyCreateRequestDTO;
import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO;

import java.util.List;

public interface TechnologyService {

    List<TechnologyResponseDTO> getTechnologies();

    TechnologyResponseDTO getTechnology(long technologyId);

    void addTechnology(TechnologyCreateRequestDTO requestDTO);

    void deleteTechnology(long technologyId);

}

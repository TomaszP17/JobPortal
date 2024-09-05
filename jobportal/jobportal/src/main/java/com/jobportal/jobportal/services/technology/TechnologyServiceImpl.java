package com.jobportal.jobportal.services.technology;

import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO;
import com.jobportal.jobportal.entities.offer.Technology;
import com.jobportal.jobportal.exceptions.TechnologyDoesNotExistsException;
import com.jobportal.jobportal.repositories.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService{

    private final TechnologyRepository technologyRepository;

    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<TechnologyResponseDTO> getTechnologies() {
        return technologyRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public TechnologyResponseDTO getTechnology(long technologyId) {
        Technology technology = technologyRepository
                .findById(technologyId)
                .orElseThrow(() -> new TechnologyDoesNotExistsException("Technology with that id: " + technologyId + "does not exists"));

        return convertToDTO(technology);
    }

    private TechnologyResponseDTO convertToDTO(Technology technology){
        return new TechnologyResponseDTO(
                technology.getId(),
                technology.getName()
        );
    }
}

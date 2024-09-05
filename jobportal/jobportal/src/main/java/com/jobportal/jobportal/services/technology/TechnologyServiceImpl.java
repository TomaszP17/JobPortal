package com.jobportal.jobportal.services.technology;

import com.jobportal.jobportal.dtos.technology.TechnologyCreateRequestDTO;
import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO;
import com.jobportal.jobportal.entities.offer.Technology;
import com.jobportal.jobportal.exceptions.TechnologyDoesNotExistsException;
import com.jobportal.jobportal.repositories.TechnologyRepository;
import jakarta.transaction.Transactional;
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

    @Override
    @Transactional
    public void addTechnology(TechnologyCreateRequestDTO requestDTO) {
        Technology technology = convertToEntity(requestDTO);

        technologyRepository.save(technology);
    }

    @Override
    @Transactional
    public void deleteTechnology(long technologyId) {
        Technology technology = technologyRepository
                .findById(technologyId)
                .orElseThrow(() -> new TechnologyDoesNotExistsException("Technology with that id: " + technologyId + "does not exists"));

        technologyRepository.delete(technology);
    }

    private TechnologyResponseDTO convertToDTO(Technology technology){
        return new TechnologyResponseDTO(
                technology.getId(),
                technology.getName()
        );
    }

    private Technology convertToEntity(TechnologyCreateRequestDTO requestDTO){
        Technology technology = new Technology();
        technology.setName(requestDTO.getName());
        return technology;
    }
}

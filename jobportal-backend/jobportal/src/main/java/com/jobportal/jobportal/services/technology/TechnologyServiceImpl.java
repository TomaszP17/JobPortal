package com.jobportal.jobportal.services.technology;

import com.jobportal.jobportal.dtos.technology.TechnologyCreateRequestDTO;
import com.jobportal.jobportal.dtos.technology.TechnologyResponseDTO;
import com.jobportal.jobportal.entities.offer.Technology;
import com.jobportal.jobportal.exceptions.offer.TechnologyDoesNotExistsException;
import com.jobportal.jobportal.mappers.OfferMapper;
import com.jobportal.jobportal.repositories.TechnologyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService{
    private final TechnologyRepository technologyRepository;
    private final OfferMapper offerMapper;
    public TechnologyServiceImpl(TechnologyRepository technologyRepository, OfferMapper offerMapper) {
        this.technologyRepository = technologyRepository;
        this.offerMapper = offerMapper;
    }
    @Override
    public List<TechnologyResponseDTO> getTechnologies() {
        return technologyRepository
                .findAll()
                .stream()
                .map(offerMapper::toTechnologyResponseDTOFromTechnology)
                .toList();
    }
    @Override
    public TechnologyResponseDTO getTechnology(long technologyId) {
        Technology technology = technologyRepository
                .findById(technologyId)
                .orElseThrow(() -> new TechnologyDoesNotExistsException("Technology with that id: " + technologyId + "does not exists"));

        return offerMapper.toTechnologyResponseDTOFromTechnology(technology);
    }
    @Override
    @Transactional
    public void addTechnology(TechnologyCreateRequestDTO requestDTO) {
        Technology technology = offerMapper.toTechnologyFromCreateRequestDTO(requestDTO);
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
}

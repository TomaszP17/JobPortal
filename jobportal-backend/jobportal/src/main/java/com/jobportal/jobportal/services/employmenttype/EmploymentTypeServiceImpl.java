package com.jobportal.jobportal.services.employmenttype;

import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeResponseDTO;
import com.jobportal.jobportal.entities.offer.EmploymentType;
import com.jobportal.jobportal.exceptions.offer.EmploymentTypeDoesNotExistsException;
import com.jobportal.jobportal.mappers.OfferMapper;
import com.jobportal.jobportal.repositories.EmploymentTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentTypeServiceImpl implements EmploymentTypeService{
    private final EmploymentTypeRepository employmentTypeRepository;
    private final OfferMapper offerMapper;
    public EmploymentTypeServiceImpl(EmploymentTypeRepository employmentTypeRepository, OfferMapper offerMapper) {
        this.employmentTypeRepository = employmentTypeRepository;
        this.offerMapper = offerMapper;
    }
    @Override
    public List<EmploymentTypeResponseDTO> getAllEmploymentTypes() {
        return employmentTypeRepository
                .findAll()
                .stream()
                .map(offerMapper::toEmploymentTypeResponseDTOFromEmploymentType)
                .toList();
    }
    @Override
    public EmploymentTypeResponseDTO getEmploymentType(long employmentTypeId) {

        EmploymentType employmentType = employmentTypeRepository
                .findById(employmentTypeId)
                .orElseThrow(() -> new EmploymentTypeDoesNotExistsException("Employment Type with that id: " + employmentTypeId + " does not exists"));

        return offerMapper.toEmploymentTypeResponseDTOFromEmploymentType(employmentType);
    }
    @Override
    @Transactional
    public void addEmploymentType(EmploymentTypeCreateRequestDTO requestDTO) {
        EmploymentType employmentType = offerMapper.toEmploymentTypeFromCreateRequestDTO(requestDTO);
        employmentTypeRepository.save(employmentType);
    }
    @Override
    @Transactional
    public void deleteEmploymentType(long employmentTypeId) {
        EmploymentType employmentType = employmentTypeRepository
                .findById(employmentTypeId)
                .orElseThrow(() -> new EmploymentTypeDoesNotExistsException("Employment Type with that id: " + employmentTypeId + " does not exists"));

        employmentTypeRepository.delete(employmentType);
    }
}

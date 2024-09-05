package com.jobportal.jobportal.services.employmenttype;

import com.jobportal.jobportal.dtos.employmenttype.EmploymentTypeResponseDTO;
import com.jobportal.jobportal.entities.offer.EmploymentType;
import com.jobportal.jobportal.exceptions.EmploymentTypeDoesNotExistsException;
import com.jobportal.jobportal.repositories.EmploymentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentTypeServiceImpl implements EmploymentTypeService{

    private final EmploymentTypeRepository employmentTypeRepository;

    public EmploymentTypeServiceImpl(EmploymentTypeRepository employmentTypeRepository) {
        this.employmentTypeRepository = employmentTypeRepository;
    }

    @Override
    public List<EmploymentTypeResponseDTO> getAllEmploymentTypes() {
        return employmentTypeRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public EmploymentTypeResponseDTO getEmploymentType(long employmentTypeId) {

        EmploymentType employmentType = employmentTypeRepository
                .findById(employmentTypeId)
                .orElseThrow(() -> new EmploymentTypeDoesNotExistsException("Employment Type with that id: " + employmentTypeId + " does not exists"));

        return convertToDTO(employmentType);
    }

    private EmploymentTypeResponseDTO convertToDTO(EmploymentType employmentType){
        return new EmploymentTypeResponseDTO(
                employmentType.getId(),
                employmentType.getName()
        );
    }
}

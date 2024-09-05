package com.jobportal.jobportal.services.worktype;

import com.jobportal.jobportal.dtos.worktype.WorkTypeResponseDTO;
import com.jobportal.jobportal.entities.offer.WorkType;
import com.jobportal.jobportal.exceptions.WorkTypeDoesNotExistsException;
import com.jobportal.jobportal.repositories.WorkTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTypeServiceImpl implements WorkTypeService{

    private final WorkTypeRepository workTypeRepository;

    public WorkTypeServiceImpl(WorkTypeRepository workTypeRepository) {
        this.workTypeRepository = workTypeRepository;
    }

    @Override
    public List<WorkTypeResponseDTO> getAllWorkTypes() {
        return workTypeRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public WorkTypeResponseDTO getWorkType(long workTypeId) {

        WorkType workType = workTypeRepository
                .findById(workTypeId)
                .orElseThrow(() -> new WorkTypeDoesNotExistsException("Work type with that id: " + workTypeId + " does not exists"));

        return convertToDTO(workType);
    }

    private WorkTypeResponseDTO convertToDTO(WorkType workType){
        return new WorkTypeResponseDTO(
                workType.getId(),
                workType.getName()
        );
    }
}

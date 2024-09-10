package com.jobportal.jobportal.services.worktype;

import com.jobportal.jobportal.dtos.worktype.WorkTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.worktype.WorkTypeResponseDTO;
import com.jobportal.jobportal.entities.offer.WorkType;
import com.jobportal.jobportal.exceptions.offer.WorkTypeDoesNotExistsException;
import com.jobportal.jobportal.mappers.OfferMapper;
import com.jobportal.jobportal.repositories.WorkTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTypeServiceImpl implements WorkTypeService{
    private final WorkTypeRepository workTypeRepository;
    private final OfferMapper offerMapper;
    public WorkTypeServiceImpl(WorkTypeRepository workTypeRepository, OfferMapper offerMapper) {
        this.workTypeRepository = workTypeRepository;
        this.offerMapper = offerMapper;
    }
    @Override
    public List<WorkTypeResponseDTO> getAllWorkTypes() {
        return workTypeRepository
                .findAll()
                .stream()
                .map(offerMapper::toWorkTypeResponseDTOFromWorkType)
                .toList();
    }
    @Override
    public WorkTypeResponseDTO getWorkType(long workTypeId) {

        WorkType workType = workTypeRepository
                .findById(workTypeId)
                .orElseThrow(() -> new WorkTypeDoesNotExistsException("Work type with that id: " + workTypeId + " does not exists"));

        return offerMapper.toWorkTypeResponseDTOFromWorkType(workType);
    }
    @Override
    @Transactional
    public void addWorkType(WorkTypeCreateRequestDTO requestDTO) {
        WorkType workType = offerMapper.toWorkTypeFromCreateRequestDTO(requestDTO);
        workTypeRepository.save(workType);
    }
    @Override
    @Transactional
    public void deleteWorkType(long workTypeId) {
        WorkType workType = workTypeRepository
                .findById(workTypeId)
                .orElseThrow(() -> new WorkTypeDoesNotExistsException("Work type with that id: " + workTypeId + " does not exists"));

        workTypeRepository.delete(workType);
    }
}

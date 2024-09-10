package com.jobportal.jobportal.services.worktype;

import com.jobportal.jobportal.dtos.worktype.WorkTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.worktype.WorkTypeResponseDTO;

import java.util.List;

public interface WorkTypeService {
    List<WorkTypeResponseDTO> getAllWorkTypes();
    WorkTypeResponseDTO getWorkType(long workTypeId);
    void addWorkType(WorkTypeCreateRequestDTO requestDTO);
    void deleteWorkType(long workTypeId);
}

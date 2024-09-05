package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.worktype.WorkTypeCreateRequestDTO;
import com.jobportal.jobportal.dtos.worktype.WorkTypeResponseDTO;
import com.jobportal.jobportal.services.worktype.WorkTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-types")
public class WorkTypeController {

    private final WorkTypeService workTypeService;

    public WorkTypeController(WorkTypeService workTypeService) {
        this.workTypeService = workTypeService;
    }

    @GetMapping
    public ResponseEntity<List<WorkTypeResponseDTO>> getAllWorkTypes(){
        return new ResponseEntity<>(workTypeService.getAllWorkTypes(), HttpStatus.OK);
    }

    @GetMapping("/{workTypeId}")
    public ResponseEntity<WorkTypeResponseDTO> getWorkType(@PathVariable long workTypeId){
        return new ResponseEntity<>(workTypeService.getWorkType(workTypeId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addWorkType(@Valid @RequestBody WorkTypeCreateRequestDTO requestDTO){
        workTypeService.addWorkType(requestDTO);
        return new ResponseEntity<>("Created WorkType successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/{workTypeId}")
    public ResponseEntity<String> deleteWorkType(@PathVariable long workTypeId){
        workTypeService.deleteWorkType(workTypeId);
        return new ResponseEntity<>("Deleted WorkType successfully!", HttpStatus.OK);
    }
}

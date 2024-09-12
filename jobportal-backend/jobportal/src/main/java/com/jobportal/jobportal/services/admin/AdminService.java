package com.jobportal.jobportal.services.admin;

import com.jobportal.jobportal.dtos.admin.AdminResponseDTO;
import com.jobportal.jobportal.dtos.admin.CreateAdminRequestDTO;
import com.jobportal.jobportal.entities.user.Admin;

import java.util.List;

public interface AdminService {
    void createAdmin(CreateAdminRequestDTO createAdminDTO);
    List<AdminResponseDTO> getAllAdmins();
}

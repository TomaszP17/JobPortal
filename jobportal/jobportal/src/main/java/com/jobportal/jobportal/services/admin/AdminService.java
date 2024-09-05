package com.jobportal.jobportal.services.admin;

import com.jobportal.jobportal.dtos.admin.CreateAdminRequestDTO;
import com.jobportal.jobportal.entities.user.Admin;

import java.util.List;

public interface AdminService {
    Admin createAdmin(CreateAdminRequestDTO createAdminDTO);
    List<Admin> getAllAdmins();
}

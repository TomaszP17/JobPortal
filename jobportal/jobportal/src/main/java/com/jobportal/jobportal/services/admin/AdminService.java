package com.jobportal.jobportal.services.admin;

import com.jobportal.jobportal.dtos.admin.CreateAdminDTO;
import com.jobportal.jobportal.entities.user.Admin;

import java.util.List;

public interface AdminService {
    Admin createAdmin(CreateAdminDTO createAdminDTO);
    List<Admin> getAllAdmins();
}

package com.jobportal.jobportal.services.admin;

import com.jobportal.jobportal.dtos.admin.CreateAdminDTO;
import com.jobportal.jobportal.entities.user.Admin;
import com.jobportal.jobportal.entities.user.Authority;
import com.jobportal.jobportal.entities.user.User;
import com.jobportal.jobportal.entities.user.UserAuthority;
import com.jobportal.jobportal.repositories.AdminRepository;
import com.jobportal.jobportal.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    private final AuthorityRepository authorityRepository;

    public AdminServiceImpl(AdminRepository adminRepository, AuthorityRepository authorityRepository) {
        this.adminRepository = adminRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Admin createAdmin(CreateAdminDTO createAdminDTO) {
        Admin admin = Admin.builder()
                .nickname(createAdminDTO.nickname())
                .email(createAdminDTO.email())
                .githubLink(createAdminDTO.githubLink())
                .linkedinLink(createAdminDTO.linkedinLink())
                .build();

        UserAuthority authority = UserAuthority.builder()
                .user(admin)
                .authority(authorityRepository.findByName("ADMIN"))
                .build();

        admin.setUserAuthority(Set.of(authority));

        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}

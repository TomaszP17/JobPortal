package com.jobportal.jobportal.services.admin;

import com.jobportal.jobportal.dtos.admin.CreateAdminRequestDTO;
import com.jobportal.jobportal.entities.user.Admin;
import com.jobportal.jobportal.entities.user.Authority;
import com.jobportal.jobportal.entities.user.UserAuthority;
import com.jobportal.jobportal.repositories.AdminRepository;
import com.jobportal.jobportal.repositories.AuthorityRepository;
import com.jobportal.jobportal.repositories.UserAuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    private final AuthorityRepository authorityRepository;

    private final UserAuthorityRepository userAuthorityRepository;

    public AdminServiceImpl(AdminRepository adminRepository,
                            AuthorityRepository authorityRepository,
                            UserAuthorityRepository userAuthorityRepository) {
        this.adminRepository = adminRepository;
        this.authorityRepository = authorityRepository;
        this.userAuthorityRepository = userAuthorityRepository;
    }

    @Transactional
    @Override
    public Admin createAdmin(CreateAdminRequestDTO createAdminDTO) {

        Admin admin = Admin.builder()
                .nickname(createAdminDTO.nickname())
                .email(createAdminDTO.email())
                .githubLink(createAdminDTO.githubLink())
                .linkedinLink(createAdminDTO.linkedinLink())
                .build();

        admin = adminRepository.save(admin);

        Authority authority = authorityRepository.findByName("ADMIN");

        UserAuthority userAuthority = UserAuthority.builder()
                .user(admin)
                .authority(authority)
                .build();

        userAuthorityRepository.save(userAuthority);

        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}

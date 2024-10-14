package com.jobportal.jobportal.dtos.company;

import com.jobportal.jobportal.dtos.user.UserDTO;

public record CurrentUserCompanyDTO(Long id, String userType, String email, String name) implements UserDTO {
}

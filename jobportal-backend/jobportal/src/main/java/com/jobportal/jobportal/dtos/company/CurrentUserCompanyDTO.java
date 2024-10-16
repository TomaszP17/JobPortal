package com.jobportal.jobportal.dtos.company;

import com.jobportal.jobportal.dtos.user.UserDTO;

import java.util.HashMap;
import java.util.Map;

public record CurrentUserCompanyDTO(Long id, String userType, String email, String name) implements UserDTO {
}

HashMap

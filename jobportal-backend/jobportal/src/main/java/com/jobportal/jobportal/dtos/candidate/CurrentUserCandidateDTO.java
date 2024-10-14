package com.jobportal.jobportal.dtos.candidate;

import com.jobportal.jobportal.dtos.user.UserDTO;

public record CurrentUserCandidateDTO(Long id, String userType, String email, String firstName) implements UserDTO {
}

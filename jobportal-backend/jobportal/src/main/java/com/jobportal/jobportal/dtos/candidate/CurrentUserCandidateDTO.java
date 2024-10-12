package com.jobportal.jobportal.dtos.candidate;

import com.jobportal.jobportal.dtos.user.CurrentUserDTO;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

public record CurrentUserCandidateDTO(String firstName,

                                      String lastName,

                                      Integer experienceYears,

                                      String email,

                                      String githubLink,

                                      String linkedinLink,

                                      String role
                                      ) implements CurrentUserDTO {
}

package com.jobportal.jobportal.dtos.company;

import com.jobportal.jobportal.dtos.user.CurrentUserDTO;
import org.hibernate.validator.constraints.URL;

public record CurrentUserCompanyDTO(String name,
                                    String nip,
                                    String email,
                                    String linkedinLink,
                                    String role
                                      )
                                      implements CurrentUserDTO {
}

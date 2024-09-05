package com.jobportal.jobportal.entities.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "admin_user")
@Data
@DiscriminatorValue("ADMIN")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Admin extends User {

    @Length(max = 50, min = 3)
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

}

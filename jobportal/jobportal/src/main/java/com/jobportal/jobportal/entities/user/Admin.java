package com.jobportal.jobportal.entities.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "admin")
@Data
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @Length(max = 50)
    @Column(name = "nickname", nullable = false)
    private String nickname;

}

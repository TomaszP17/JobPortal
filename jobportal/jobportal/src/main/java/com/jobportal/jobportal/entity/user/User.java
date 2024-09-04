package com.jobportal.jobportal.entity.user;

import com.jobportal.jobportal.entity.UserFavouriteOffer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "my_user")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Email
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @URL
    @Column(name = "github_link", length = 255)
    private String githubLink;

    @URL
    @Column(name = "linkedin_link", length = 255)
    private String linkedinLink;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAuthority> userAuthority = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserFavouriteOffer> userFavouriteOffers = new HashSet<>();

}

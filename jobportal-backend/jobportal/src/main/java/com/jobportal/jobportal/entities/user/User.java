package com.jobportal.jobportal.entities.user;

import com.jobportal.jobportal.entities.UserFavouriteOffer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "my_user")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Email
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @URL
    @Column(name = "github_link")
    private String githubLink;

    @URL
    @Column(name = "linkedin_link")
    private String linkedinLink;

    @Column(name = "is_completed")
    private Boolean isCompleted = Boolean.TRUE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAuthority> userAuthority = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserFavouriteOffer> userFavouriteOffers = new HashSet<>();

}

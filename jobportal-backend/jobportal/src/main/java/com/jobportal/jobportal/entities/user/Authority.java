package com.jobportal.jobportal.entities.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authority")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 25)
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAuthority> userAuthority = new HashSet<>();

}

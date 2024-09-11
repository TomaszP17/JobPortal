package com.jobportal.jobportal.entities;

import com.jobportal.jobportal.entities.user.Candidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pdf")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "s3_uuid", nullable = false, unique = true, length = 36)
    private String s3Uuid;

    @Column(name = "file_name", length = 255)
    private String fileName;

    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate = LocalDateTime.now();

    @OneToOne(mappedBy = "pdf", cascade = CascadeType.ALL)
    private Application application;

    @OneToOne(mappedBy = "pdf", cascade = CascadeType.ALL)
    private Candidate candidate;

}

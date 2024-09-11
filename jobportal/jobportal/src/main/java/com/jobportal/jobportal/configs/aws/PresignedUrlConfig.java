package com.jobportal.jobportal.configs.aws;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Getter
@Setter
@Validated
public class PresignedUrlConfig {

    @NotNull(message = "S3 presigned URL validity must be specified")
    @Positive(message = "S3 presigned URL validity must be a positive value")
    private Integer validity;

    public Duration getValidityDuration() {
        return Duration.ofSeconds(validity);
    }
}

package com.jobportal.jobportal.configs.aws;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "jobportal.jobportal.aws.s3")
public class AwsS3BucketConfig {

    @NotBlank(message = "S3 bucket name must be configured")
    private String bucketName;

    @Valid
    private PresignedUrlConfig presignedUrl = new PresignedUrlConfig();
}

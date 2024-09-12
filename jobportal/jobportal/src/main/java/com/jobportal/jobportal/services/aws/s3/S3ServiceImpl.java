package com.jobportal.jobportal.services.aws.s3;

import com.jobportal.jobportal.configs.aws.AwsS3BucketConfig;
import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsS3BucketConfig.class)
public class S3ServiceImpl implements S3Service {

    private final S3Template s3Template;
    private final AwsS3BucketConfig awsS3BucketConfig;

    @Override
    @SneakyThrows
    public String uploadPdf(@NonNull final MultipartFile file) {
        String key = UUID.randomUUID() + ".pdf";
        String bucketName = awsS3BucketConfig.getBucketName();

        s3Template.upload(bucketName, key, file.getInputStream());

        return key;
    }

    @Override
    public void deletePdf(@NonNull final String uuid) {
        String bucketName = awsS3BucketConfig.getBucketName();
        s3Template.deleteObject(bucketName, uuid);
    }

    @Override
    public URL generateDownloadUrl(@NonNull final String uuid) {
        String bucketName = awsS3BucketConfig.getBucketName();
        Duration urlValidity = Duration.ofMinutes(20);

        return s3Template.createSignedGetURL(bucketName, uuid, urlValidity);
    }

    @Override
    @SneakyThrows
    public InputStream getPdfStream(@NonNull final String uuid) {
        String bucketName = awsS3BucketConfig.getBucketName();
        return s3Template.download(bucketName, uuid).getInputStream();
    }

//    @SneakyThrows
//    public String getPdfName(@NonNull final String pdfUuid) {
//        return s3Template.
//    }
}

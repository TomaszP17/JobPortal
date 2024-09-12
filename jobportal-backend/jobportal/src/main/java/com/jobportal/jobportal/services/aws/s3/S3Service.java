package com.jobportal.jobportal.services.aws.s3;

import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;

public interface S3Service {

    String uploadPdf(@NonNull MultipartFile file);

    void deletePdf(@NonNull String uuid);

    URL generateDownloadUrl(@NonNull String uuid);

    InputStream getPdfStream(@NonNull String uuid);

}

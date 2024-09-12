package com.jobportal.jobportal.dtos.pdf;

import java.io.InputStream;

public record PdfDownloadResponseDTO(
        InputStream pdfInputStream,
        String fileName
) {
}

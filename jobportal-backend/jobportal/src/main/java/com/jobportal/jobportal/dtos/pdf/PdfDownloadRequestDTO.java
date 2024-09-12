package com.jobportal.jobportal.dtos.pdf;

public record PdfDownloadRequestDTO(
        Long pdfId,
        String pdfName
) {
}

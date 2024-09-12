package com.jobportal.jobportal.services.pdf;

import com.jobportal.jobportal.dtos.pdf.PdfDownloadRequestDTO;
import com.jobportal.jobportal.dtos.pdf.PdfDownloadResponseDTO;
import com.jobportal.jobportal.entities.Pdf;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface PdfService {

    Pdf uploadPdf(MultipartFile pdf, String userName);

    void deletePdf(Long id);

    PdfDownloadResponseDTO downloadPdf(Long pdfId);
}

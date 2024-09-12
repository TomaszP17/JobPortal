package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.pdf.PdfDownloadRequestDTO;
import com.jobportal.jobportal.dtos.pdf.PdfDownloadResponseDTO;
import com.jobportal.jobportal.services.pdf.PdfService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@RestController
@RequestMapping("/api/pdf/")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    // nie przekazywać request parama, tylko przechowywac w bazie danych
    @GetMapping("/view/{pdfId}")
    public ResponseEntity<InputStreamResource> viewPdf(@PathVariable Long pdfId) {
        try {
            PdfDownloadResponseDTO pdfDownloadResponseDTO = pdfService.downloadPdf(pdfId);
            InputStream inputStream = pdfDownloadResponseDTO.pdfInputStream();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().filename(pdfDownloadResponseDTO.fileName()).build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

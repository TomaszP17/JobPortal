package com.jobportal.jobportal.services.pdf;

import com.jobportal.jobportal.dtos.pdf.PdfDownloadRequestDTO;
import com.jobportal.jobportal.dtos.pdf.PdfDownloadResponseDTO;
import com.jobportal.jobportal.entities.Pdf;
import com.jobportal.jobportal.repositories.PdfRepository;
import com.jobportal.jobportal.services.aws.s3.S3Service;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class PdfServiceImpl implements PdfService {

    private final S3Service s3Service;
    private final PdfRepository pdfRepository;

    public PdfServiceImpl(S3Service s3Service, PdfRepository pdfRepository) {
        this.s3Service = s3Service;
        this.pdfRepository = pdfRepository;
    }

    @Override
    public Pdf uploadPdf(MultipartFile pdf, String userName) {
        String uuid = s3Service.uploadPdf(pdf);

        Pdf pdfFile = Pdf.builder()
                .s3Uuid(uuid)
                .build();

        return pdfRepository.save(pdfFile);
    }

    @Transactional
    @Override
    public void deletePdf(Long id) {
        Pdf pdf = pdfRepository.findById(id).orElse(null);
        s3Service.deletePdf(pdf.getS3Uuid());
        pdfRepository.deleteById(id);
    }

    public PdfDownloadResponseDTO downloadPdf(Long pdfId, String pdfName) {
        try {
            Pdf pdf = pdfRepository.findById(pdfId).orElseThrow(RuntimeException::new);

            InputStream inputStream = s3Service.getPdfStream(pdf.getS3Uuid());

            return new PdfDownloadResponseDTO(inputStream, pdfName);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching and modifying the PDF filename", e);
        }
    }


}

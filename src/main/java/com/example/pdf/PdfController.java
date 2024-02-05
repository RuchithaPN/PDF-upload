package com.example.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://localhost:4200")
public class PdfController {

    @PostMapping("/upload")
    public String uploadPdf(@RequestParam("file") MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(file.getBytes()))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Set options for maintaining columns, if necessary
            // For example, you can use pdfStripper.setSortByPosition(true);

            String pdfText = pdfStripper.getText(document);
            return pdfText;
        }
    }
}

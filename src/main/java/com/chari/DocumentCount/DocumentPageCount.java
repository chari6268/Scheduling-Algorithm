package com.chari.DocumentCount;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public class DocumentPageCount {

    public static void main(String[] args) {
        File file = new File("D:\\D Drive\\Projects\\Scheduling\\src\\main\\java\\com\\chari\\DocumentCount\\sample.pdf");
        String extension = getFileExtension(file);

        if (extension.equals("pdf")) {
            getPdfPageCount(file);
        } else {
            System.out.println("Unsupported file format");
        }
    }
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    private static void getPdfPageCount(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            int pageCount = document.getNumberOfPages();
            System.out.println("Number of Pages in PDF: " + pageCount);

            PDFTextStripper stripper = new PDFTextStripper();
            String content = stripper.getText(document);
            System.out.println("\nPDF Content:\n" + content);
        } catch (IOException e) {
            System.out.println("Error reading PDF file: " + e.getMessage());
        }
    }
}

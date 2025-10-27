package main.java.pdf.feature.extract;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PDFFeatureExtractor {
    public static class PdfFeatures {
        public int pageCount;
        public PDPage firstPage;
        public float firstPagebleed;
    }

    public static PdfFeatures extractFeatures(String pdfPath) {
        PdfFeatures features = new PdfFeatures();

        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            features.pageCount = document.getNumberOfPages();
            var firstPage = document.getPages().get(0);

            features.firstPage = firstPage;
            features.firstPagebleed = calculateBleed(firstPage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return features;
    }

    public static PdfFeatures extractFeatures(InputStream pdfInputStream) {
        PdfFeatures features = new PdfFeatures();

        try (PDDocument document = PDDocument.load(pdfInputStream)) {
            features.pageCount = document.getNumberOfPages();
            var firstPage = document.getPages().get(0);

            features.firstPage = firstPage;
            features.firstPagebleed = calculateBleed(firstPage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return features;
    }

    private static float calculateBleed(PDPage page) {
        PDRectangle trimBox = page.getTrimBox();
        PDRectangle bleedBox = page.getBleedBox();

        if (trimBox == null || bleedBox == null) {
            System.out.println("TrimBox or BleedBox not defined.");
            return 0;
        }

        // Compute horizontal and vertical bleed extensions
        float bleedLeft = Math.abs(trimBox.getLowerLeftX() - bleedBox.getLowerLeftX());
        float bleedRight = Math.abs(bleedBox.getUpperRightX() - trimBox.getUpperRightX());
        float bleedTop = Math.abs(bleedBox.getUpperRightY() - trimBox.getUpperRightY());
        float bleedBottom = Math.abs(trimBox.getLowerLeftY() - bleedBox.getLowerLeftY());

        // Average bleed in points
        return (bleedLeft + bleedRight + bleedTop + bleedBottom) / 4.0f;
    }
}

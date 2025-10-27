package main.java.pdf.feature.ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PDFToImageConverter {
    public static void convertPdfToImages(String pdfPath, String outputDir) throws IOException {
        PDDocument document = PDDocument.load(new File(pdfPath));
        PDFRenderer pdfRenderer = new PDFRenderer(document);

        int pageCount = document.getNumberOfPages();

        for (int page = 0; page < pageCount; ++page) {
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300);

            String fileName = outputDir + File.separator + "page_" + (page + 1) + ".png";
            ImageIO.write(bufferedImage, "PNG", new File(fileName));
        }

        document.close();
    }
}

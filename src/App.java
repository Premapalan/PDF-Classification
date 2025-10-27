
import main.java.pdf.feature.extract.PDFFeatureExtractor;
import static main.java.utility.LogUtility.LOG;

import java.net.URL;

public class App {
    // static final String TRAININGDATA_PATH =
    // "/Users/prasathpremapalan/Development/TrainingData";
    // static final String BOOKS_DATA = TRAININGDATA_PATH.concat("/Books");
    // static final String ADVERTISING_DATA =
    // TRAININGDATA_PATH.concat("/Advertising");
    // static final String BROSCHURES_DATA =
    // TRAININGDATA_PATH.concat("/Broschures");
    // static final String BUSINESSCARDS_DATA =
    // TRAININGDATA_PATH.concat("/BussinesCards");
    // static final String NEWSPAPER_DATA = TRAININGDATA_PATH.concat("/Newspaper");
    // static final String MAGAZINES_DATA = TRAININGDATA_PATH.concat("/Magazines");

    static final Class<App> CLASS = App.class;

    public static void main(String[] args) {
        String resourcePath = "main/resources/pdf/32p_1.1_FolderZeitungsbeilageRZ+++3186911.pdf";
        var inputStream = CLASS.getClassLoader().getResourceAsStream(resourcePath);
        var pdfFeatures = PDFFeatureExtractor.extractFeatures(inputStream);
        System.out.println(pdfFeatures.pageCount);

        var trimBox = pdfFeatures.firstPage.getTrimBox();
        var bleedBox = pdfFeatures.firstPage.getBleedBox();
        var cropBox = pdfFeatures.firstPage.getCropBox();
        var bbox = pdfFeatures.firstPage.getBBox();

        LOG("TrimBox: %.2f x %.2f", trimBox.getWidth(), trimBox.getHeight());
        LOG("CropBox: %.2f x %.2f", cropBox.getWidth(), cropBox.getHeight());
        LOG("BleedBox: %.2f x %.2f", bleedBox.getWidth(), bleedBox.getHeight());
        LOG("BBox: %.2f x %.2f", bbox.getWidth(), bbox.getHeight());
        LOG("Bleed: %.2f", pdfFeatures.firstPagebleed);
    }
}

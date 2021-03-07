package dhbw.demo.text_extractor;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;

public class WordTextExtractor {
    private final String errorMessage = "Text could not be extracted";

    public String extractText(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            XWPFDocument wordDocument = new XWPFDocument(fileInputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(wordDocument);
            String extractedText = extractor.getText();

            fileInputStream.close();
            return extractedText;
        } catch (Exception e) {
            throw new RuntimeException(errorMessage);
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

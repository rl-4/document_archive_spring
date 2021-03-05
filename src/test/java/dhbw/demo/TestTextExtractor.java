package dhbw.demo;

import dhbw.demo.text_extractor.ExcelTextExtractor;
import dhbw.demo.text_extractor.TextExtractor;
import dhbw.demo.text_extractor.WordTextExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestTextExtractor {
    @Test
    public void testWordExtractor() {
        TextExtractor textExtractor = new WordTextExtractor();
        String separator = File.separator;
        String filePath = "src" + separator + "test" + separator + "java" + separator + "test_resources" + separator + "Test.docx";
        String extractedText = textExtractor.extractText(filePath);

        String expectedFileContent = "Test\n" +
                "1\n" +
                "2\n" +
                "3\n" +
                "test\n";

        Assertions.assertEquals(expectedFileContent, extractedText);
    }

    @Test
    public void testExcelExtractor() {
        TextExtractor textExtractor = new ExcelTextExtractor();
        String separator = File.separator;
        String filePath = "src" + separator + "test" + separator + "java" + separator + "test_resources" + separator + "Test.xlsx";
        String extractedText = textExtractor.extractText(filePath);

        String expectedFileContent = "Test1.02.03.04.05.0test";

        Assertions.assertEquals(expectedFileContent, extractedText);
    }

    @Test
    public void testExcelExtractorCantOpenDocx() {
        TextExtractor textExtractor = new ExcelTextExtractor();
        String separator = File.separator;
        String filePath = "src" + separator + "test" + separator + "java" + separator + "test_resources" + separator + "Test.docx";

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            String extractedText = textExtractor.extractText(filePath);
        });

        Assertions.assertEquals(textExtractor.errorMessage, exception.getMessage());
    }

    @Test
    public void testWordExtractorCantOpenXlsx() {
        TextExtractor textExtractor = new WordTextExtractor();
        String separator = File.separator;
        String filePath = "src" + separator + "test" + separator + "java" + separator + "test_resources" + separator + "Test.xlsx";

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            String extractedText = textExtractor.extractText(filePath);
        });

        Assertions.assertEquals(textExtractor.errorMessage, exception.getMessage());
    }
}

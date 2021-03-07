package dhbw.demo;

import dhbw.demo.json.Parser;
import dhbw.demo.model.MatchingDocument;
import dhbw.demo.model.MatchingDocumentsWrapper;
import dhbw.demo.model.TextExtractionResult;
import dhbw.demo.model.TextExtractionResultWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestJsonParser {
    private Parser parser;

    @BeforeEach
    public void setup() {
        parser = new Parser();
    }

    @Test
    public void testWriteJsonMatchingDocumentsWrapper() {
        //create test objects
        MatchingDocument matchingDocument1 = new MatchingDocument(1, "Test/Test.docx");
        MatchingDocument matchingDocument2 = new MatchingDocument(2, "Test/Test2.docx");
        MatchingDocument[] matchingDocuments = new MatchingDocument[]{matchingDocument1, matchingDocument2};
        MatchingDocumentsWrapper matchingDocumentsWrapper = new MatchingDocumentsWrapper(matchingDocuments);

        //convert to json string
        String actualJson = "";
        try {
            actualJson = parser.writeJsonMatchingDocumentsWrapper(matchingDocumentsWrapper);
        } catch (Exception e) {
            Assertions.fail();
        }
        String expectedJson = "{\"matchingDocuments\":[{\"document_id\":1,\"path\":\"Test/Test.docx\"},{\"document_id\":2,\"path\":\"Test/Test2.docx\"}]}";
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testWriteTextExtractionResultWrapper() {
        /*
        //create test objects
        TextExtractionResult textExtractionResult1 = new TextExtractionResult(1,"Test");
        TextExtractionResult textExtractionResult2 = new TextExtractionResult(1, new String[]{"1.0", "Test"});
        TextExtractionResult[] textExtractionResults = new TextExtractionResult[]{textExtractionResult1, textExtractionResult2};
        TextExtractionResultWrapper textExtractionResultWrapper = new TextExtractionResultWrapper(textExtractionResults);

        //convert to json string
        String actualJson = "";
        try {
            actualJson = parser.writeJsonTextExtractionResultWrapper(textExtractionResultWrapper);
        } catch (Exception e) {
            Assertions.fail();
        }
        String expectedJson = "{\"textExtractionResults\":[{\"document_id\":1,\"extractedText\":\"Test\",\"extractedCells\":null},{\"document_id\":1,\"extractedText\":null,\"extractedCells\":[\"1.0\",\"Test\"]}]}\n";
        Assertions.assertEquals(expectedJson, actualJson);

         */
    }

    @Test
    public void testReadJsonMatchingDocumentsWrapper() {
        /*
        String json = "{\"textExtractionResults\":[{\"document_id\":1,\"extractedText\":\"Test\",\"extractedCells\":[]},{\"document_id\":1,\"extractedText\":\"\",\"extractedCells\":[\"1.0\",\"Test\"]}]}";
        MatchingDocumentsWrapper actualMatchingDocumentsWrapper = null;
        try{
            actualMatchingDocumentsWrapper = parser.readJsonMatchingDocumentsWrapper(json);
        } catch (Exception e){
            Assertions.fail();
        }

        MatchingDocument matchingDocument1 = new MatchingDocument(1, "Test/Test.docx");
        MatchingDocument matchingDocument2 = new MatchingDocument(2, "Test/Test2.docx");
        MatchingDocument[] matchingDocuments = new MatchingDocument[]{matchingDocument1, matchingDocument2};
        MatchingDocumentsWrapper expectedMatchingDocumentsWrapper = new MatchingDocumentsWrapper(matchingDocuments);

        Assertions.assertEquals(expectedMatchingDocumentsWrapper, actualMatchingDocumentsWrapper);

         */
    }

    @Test
    public void testReadTextExtractionResultWrapper() {

    }
}

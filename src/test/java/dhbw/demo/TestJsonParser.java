package dhbw.demo;

import dhbw.demo.json.Parser;
import dhbw.demo.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
        String expectedJson = "{\"textExtractionResults\":[{\"document_id\":1,\"extractedText\":\"Test\",\"extractedCells\":null},{\"document_id\":1,\"extractedText\":null,\"extractedCells\":[\"1.0\",\"Test\"]}]}";
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testReadJsonMatchingDocumentsWrapper() {
        String json = "{\"matchingDocuments\":[{\"document_id\":1,\"path\":\"Test/Test.docx\"},{\"document_id\":2,\"path\":\"Test/Test2.docx\"}]}";
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

        Assertions.assertTrue(expectedMatchingDocumentsWrapper.equals(actualMatchingDocumentsWrapper));
    }

    @Test
    public void testReadTextExtractionResultWrapper() {
        String json = "{\"textExtractionResults\":[{\"document_id\":1,\"extractedText\":\"Test\",\"extractedCells\":null},{\"document_id\":1,\"extractedText\":null,\"extractedCells\":[\"1.0\",\"Test\"]}]}";
        TextExtractionResultWrapper actualTextExtractionResultWrapper = null;
        try{
            actualTextExtractionResultWrapper = parser.readJsonTextExtractionResultWrapper(json);
        } catch (Exception e){
            Assertions.fail();
        }

        TextExtractionResult textExtractionResult1 = new TextExtractionResult(1,"Test");
        TextExtractionResult textExtractionResult2 = new TextExtractionResult(1, new String[]{"1.0", "Test"});
        TextExtractionResult[] textExtractionResults = new TextExtractionResult[]{textExtractionResult1, textExtractionResult2};
        TextExtractionResultWrapper expectedTextExtractionResultWrapper = new TextExtractionResultWrapper(textExtractionResults);

        Assertions.assertTrue(expectedTextExtractionResultWrapper.equals(actualTextExtractionResultWrapper));
    }

    @Test
    public void testFilterQueryToJson(){
        Map<String, String> keyValuePairs = new HashMap<>();
        keyValuePairs.put("Author", "A");
        keyValuePairs.put("Published", "B");
        String searchQuery = "Test";
        FilterQuery filterQuery = new FilterQuery(true, searchQuery, keyValuePairs);

        String actualJson = null;
        try{
            actualJson = parser.filterQueryToJson(filterQuery);
        } catch (Exception e){
            Assertions.fail();
        }

        String expectedJson = "{\"regExMatch\":true,\"searchQuery\":\"Test\",\"keyValuePairs\":{\"Author\":\"A\",\"Published\":\"B\"}}";

        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testJsonToFilterQuery(){
        String json = "{\"regExMatch\":true,\"searchQuery\":\"Test\",\"keyValuePairs\":{\"Author\":\"A\",\"Published\":\"B\"}}";
        FilterQuery actualFilterQuery = null;
        try{
            actualFilterQuery = parser.jsonToFilterQuery(json);
        }catch (Exception e){
            Assertions.fail();
        }

        Map<String, String> keyValuePairs = new HashMap<>();
        keyValuePairs.put("Author", "A");
        keyValuePairs.put("Published", "B");
        String searchQuery = "Test";
        FilterQuery expectedFilterQuery = new FilterQuery(true, searchQuery, keyValuePairs);

        Assertions.assertTrue(expectedFilterQuery.equals(actualFilterQuery));
    }
}

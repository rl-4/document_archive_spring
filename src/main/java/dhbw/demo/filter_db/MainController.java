package dhbw.demo.filter_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    private DocumentArchiveRepository repository;

    @GetMapping("/test")
    public String getDocumentData() throws FileNotFoundException {
        //create new document
        DocumentEntity test_document = new DocumentEntity();
        test_document.name = "Test.docx";
        //test_document.documentFile = new File("src/test/java/test_resources/Test.docx");
        this.repository.save(test_document);

        List<DocumentEntity> documentEntities = this.repository.findByNameContaining("Test");
        if (documentEntities.isEmpty()) {
            return "No entries";
        }
        String documentData = documentEntities.get(0).id + ": " + documentEntities.get(0).name;

        /*
        File testFile = documentEntities.get(0).documentFile;
        WordTextExtractor textExtractor = new WordTextExtractor();
        String content = textExtractor.extractText(testFile);
        System.out.println(content);
         */

        System.out.println(documentData);
        return documentData;
    }
}

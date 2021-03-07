package dhbw.demo.filter_db;

import dhbw.demo.json.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class MainController {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private MetaDataRepository metaDataRepository;

    private Parser parser;

    public MainController() {
        parser = new Parser();
    }

    @GetMapping("/test")
    public String getDocumentData() {
        return "alright";
    }

    @GetMapping("/fullTextSearch")
    public ResponseEntity getAllDocumentsMatchingKeyValuePairs() {
        //Map<String, String> keyValuePairs = parser.parse();
        //List<DocumentEntity> matchingDocuments = getAllDocumentsMatchingKeyValuePairs(keyValuePairs);
        return new ResponseEntity("Test", HttpStatus.OK);
        // document.path -> IO -> TextExtraction -> extracted String / String[]
    }

    private List<DocumentEntity> getAllDocumentsMatchingKeyValuePairs(Map<String, String> keyValuePairs) {
        Predicate<DocumentEntity> matchesKeyValuePairs = document -> keyValuePairs.entrySet().stream().allMatch(keyValuePair -> document.getMetaData().stream().anyMatch(metaDate -> metaDate.getKey().equals(keyValuePair.getKey()) && metaDate.getValue().equals(keyValuePair.getValue())));
        List<DocumentEntity> matchingDocuments = documentRepository.findAll().stream().filter(matchesKeyValuePairs).collect(Collectors.toList());

        return matchingDocuments;
    }
}

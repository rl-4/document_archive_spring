package dhbw.demo.filter_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class MainController {
    @Autowired
    private DocumentRepository documentArchiveRepository;

    @Autowired
    private MetaDataRepository metaDataRepository;

    @GetMapping("/test")
    public String getDocumentData() {
        //create new document
        DocumentEntity test_document = new DocumentEntity("Test.docx", "test/Test.docx");

        //save document
        this.documentArchiveRepository.save(test_document);

        //create and save new metaData
        MetaDataEntity metaDataEntity = new MetaDataEntity("Author", "Tolstoi", test_document);
        test_document.getMetaData().add(metaDataEntity);
        MetaDataEntity metaDataEntity2 = new MetaDataEntity("Published", "today", test_document);
        test_document.getMetaData().add(metaDataEntity2);
        this.metaDataRepository.save(metaDataEntity);
        this.metaDataRepository.save(metaDataEntity2);

        //create new document
        DocumentEntity test_document2 = new DocumentEntity("Test.docx", "test/Test.docx");

        //save document
        this.documentArchiveRepository.save(test_document2);

        //create and save new metaData
        MetaDataEntity metaDataEntity21 = new MetaDataEntity("Author", "Ende", test_document2);
        test_document.getMetaData().add(metaDataEntity21);
        MetaDataEntity metaDataEntity22 = new MetaDataEntity("Published", "tomorrow", test_document2);
        test_document.getMetaData().add(metaDataEntity22);
        this.metaDataRepository.save(metaDataEntity21);
        this.metaDataRepository.save(metaDataEntity22);

        List<DocumentEntity> documentEntities = documentArchiveRepository.findAll();
        documentEntities.forEach(documentEntity -> System.out.println((long) documentEntity.getMetaData().size()));

        Map<String, String> keyValuePairs = new HashMap<>();
        keyValuePairs.put("Author", "Tolstoi");

        Predicate<DocumentEntity> matchesKeyValuePairs = document -> keyValuePairs.entrySet().stream().allMatch(keyValuePair -> document.getMetaData().stream().anyMatch(metaDate -> metaDate.getKey().equals(keyValuePair.getKey()) && metaDate.getValue().equals(keyValuePair.getValue())));
        List<DocumentEntity> matchingDocuments = documentArchiveRepository.findAll().stream().filter(matchesKeyValuePairs).collect(Collectors.toList());
        matchingDocuments.forEach(documentEntity -> System.out.println(documentEntity.toString()));
        System.out.println("End");
        return "alright";
    }
}

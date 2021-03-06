package dhbw.demo.filter_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        this.metaDataRepository.save(new MetaDataEntity("Author", "Tolstoi", test_document));
        this.metaDataRepository.save(new MetaDataEntity("Published", "1.1.2000", test_document));

        List<MetaDataEntity> metaDataEntities = metaDataRepository.findByKeyAndValue("Author", "Tolstoi");
        if (!metaDataEntities.isEmpty()){
            metaDataEntities.forEach(metaDataEntity -> System.out.println(metaDataEntity.toString()));
        }

        return "alright";
    }
}

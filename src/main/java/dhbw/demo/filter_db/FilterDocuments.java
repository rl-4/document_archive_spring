package dhbw.demo.filter_db;

import java.util.Map;

public class FilterDocuments {
    private final DocumentRepository documentRepository;
    private final MetaDataRepository metaDataRepository;

    public FilterDocuments(DocumentRepository documentRepository, MetaDataRepository metaDataRepository) {
        this.documentRepository = documentRepository;
        this.metaDataRepository = metaDataRepository;
    }

    public void getAllDocumentsMatchingKeyValuePairs(Map<String, String> keyValuePairs) {

    }
}

package dhbw.demo.filter_db;

import dhbw.demo.model.*;
import dhbw.demo.text_search.FilterDocumentsByFullTextSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/test")
    public String getDocumentData() {
        return "alright";
    }

    @PostMapping("/fullTextSearch")
    @ResponseBody
    public List<DocumentMetaDataDto> getAllDocumentsMatchingKeyValuePairs(@RequestBody FilterQuery filterQuery) {
        boolean regExMatch = filterQuery.regExMatch;
        String searchQuery = filterQuery.searchQuery;
        Map<String, String> keyValuePairs = filterQuery.keyValuePairs;

        List<DocumentEntity> matchingDocuments = getAllDocumentsMatchingKeyValuePairs(keyValuePairs);

        //send paths to TextExtractor
        MatchingDocumentsWrapper matchingDocumentsWrapper = mapMatchingDocumentsToMatchingDocumentsWrapper(matchingDocuments);

        //TODO get TextExtractionResults
        TextExtractionResultWrapper textExtractionResultWrapper = null;

        //fullTextSearch in TextExtractionResults
        //-> ids
        int[] filtered_document_ids = FilterDocumentsByFullTextSearch.filterDocumentsByFullTextSearch(regExMatch, textExtractionResultWrapper, searchQuery);

        //fullTextSearchResult -> Select * From documents
        List<DocumentEntity> filteredDocuments = documentRepository.findByIdIn(filtered_document_ids);

        //DataTransferObject
        List<DocumentMetaDataDto> documentMetaDataDtos = convertDocumentEntitiesToDocumentMetaDataDtoList(filteredDocuments);

        return documentMetaDataDtos;
    }

    private List<DocumentEntity> getAllDocumentsMatchingKeyValuePairs(Map<String, String> keyValuePairs) {
        Predicate<DocumentEntity> matchesKeyValuePairs = document -> keyValuePairs.entrySet().stream().allMatch(keyValuePair -> document.getMetaData().stream().anyMatch(metaDate -> metaDate.getKey().equals(keyValuePair.getKey()) && metaDate.getValue().equals(keyValuePair.getValue())));
        List<DocumentEntity> matchingDocuments = documentRepository.findAll().stream().filter(matchesKeyValuePairs).collect(Collectors.toList());

        return matchingDocuments;
    }

    private MatchingDocumentsWrapper mapMatchingDocumentsToMatchingDocumentsWrapper(List<DocumentEntity> matchingDocuments) {
        List<MatchingDocument> matchingDocumentDtos = new ArrayList<>();
        for (DocumentEntity matchingDocument : matchingDocuments) {
            MatchingDocument matchingDocumentDto = new MatchingDocument(matchingDocument.getId(), matchingDocument.getPath());
            matchingDocumentDtos.add(matchingDocumentDto);
        }
        //TODO cast works?
        return new MatchingDocumentsWrapper(matchingDocumentDtos.toArray(MatchingDocument[]::new));
    }

    private DocumentMetaDataDto convertDocumentEntityToDocumentMetaDataDto(DocumentEntity document) {
        //TODO return all  keyValuePairs?
        Map<String, String> allKeyValuePairs = document.getMetaData().stream().collect(Collectors.toMap(MetaDataEntity::getKey, MetaDataEntity::getValue));
        return new DocumentMetaDataDto(document.getName(), document.getPath(), allKeyValuePairs);
    }

    private List<DocumentMetaDataDto> convertDocumentEntitiesToDocumentMetaDataDtoList(List<DocumentEntity> filteredDocuments) {
        return filteredDocuments.stream().map(this::convertDocumentEntityToDocumentMetaDataDto).collect(Collectors.toList());
    }
}

package dhbw.demo.filter_db;

import dhbw.demo.httpSearch.HttpSearch;
import dhbw.demo.json.Parser;
import dhbw.demo.model.*;
import dhbw.demo.text_search.FilterDocumentsByFullTextSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class MainController {
    private final HttpSearch httpSearch = new HttpSearch();
    private final Parser parser = new Parser();
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private MetaDataRepository metaDataRepository;

    @PostMapping("/fullTextSearch")
    @ResponseBody
    public List<DocumentMetaDataDto> getAllDocumentsMatchingKeyValuePairs(@RequestBody FilterQuery filterQuery) {
        boolean regExMatch = filterQuery.regExMatch;
        String searchQuery = filterQuery.searchQuery;
        Map<String, String> keyValuePairs = filterQuery.keyValuePairs;

        List<DocumentEntity> matchingDocuments = getAllDocumentsMatchingKeyValuePairs(keyValuePairs);

        //send paths to TextExtractor
        MatchingDocumentsWrapper matchingDocumentsWrapper = mapMatchingDocumentsToMatchingDocumentsWrapper(matchingDocuments);

        //get ids
        String ids = Arrays.stream(matchingDocumentsWrapper.matchingDocuments).map(matchingDocument -> String.valueOf(matchingDocument.document_id)).collect(Collectors.joining(","));
        HttpResponse<String> response;
        TextExtractionResultWrapper textExtractionResultWrapper = null;
        try {
            response = httpSearch.search(new URI("localhost:8081/api/getTexts?ids=[" + ids + "]"));
            String json = response.body();
            textExtractionResultWrapper = parser.readJsonTextExtractionResultWrapper(json);
        } catch (Exception e) {
            //TODO give back specific status code
        }

        //fullTextSearch in TextExtractionResults
        //-> ids
        int[] filtered_document_ids = FilterDocumentsByFullTextSearch.filterDocumentsByFullTextSearch(regExMatch, textExtractionResultWrapper, searchQuery);

        //fullTextSearchResult -> Select * From documents
        List<DocumentEntity> filteredDocuments = documentRepository.findByIdIn(filtered_document_ids);

        //DataTransferObject
        return convertDocumentEntitiesToDocumentMetaDataDtoList(filteredDocuments);
    }

    private List<DocumentEntity> getAllDocumentsMatchingKeyValuePairs(Map<String, String> keyValuePairs) {
        Predicate<DocumentEntity> matchesKeyValuePairs = document -> keyValuePairs.entrySet().stream().allMatch(keyValuePair -> document.getMetaData().stream().anyMatch(metaDate -> metaDate.getKey().equals(keyValuePair.getKey()) && metaDate.getValue().equals(keyValuePair.getValue())));
        List<DocumentEntity> matchingDocuments = documentRepository.findAll().stream().filter(matchesKeyValuePairs).collect(Collectors.toList());

        return matchingDocuments;
    }

    private MatchingDocumentsWrapper mapMatchingDocumentsToMatchingDocumentsWrapper(List<DocumentEntity> matchingDocuments) {
        List<MatchingDocument> matchingDocumentDTOs = new ArrayList<>();
        for (DocumentEntity matchingDocument : matchingDocuments) {
            MatchingDocument matchingDocumentDto = new MatchingDocument(matchingDocument.getId(), matchingDocument.getPath());
            matchingDocumentDTOs.add(matchingDocumentDto);
        }
        //TODO cast works?
        return new MatchingDocumentsWrapper(matchingDocumentDTOs.toArray(MatchingDocument[]::new));
    }

    private DocumentMetaDataDto convertDocumentEntityToDocumentMetaDataDto(DocumentEntity document) {
        Map<String, String> allKeyValuePairs = document.getMetaData().stream().collect(Collectors.toMap(MetaDataEntity::getKey, MetaDataEntity::getValue));
        return new DocumentMetaDataDto(document.getName(), document.getPath(), allKeyValuePairs);
    }

    private List<DocumentMetaDataDto> convertDocumentEntitiesToDocumentMetaDataDtoList(List<DocumentEntity> filteredDocuments) {
        return filteredDocuments.stream().map(this::convertDocumentEntityToDocumentMetaDataDto).collect(Collectors.toList());
    }
}

package dhbw.demo.filter_db;

import dhbw.demo.httpSearch.HttpSearch;
import dhbw.demo.json.Parser;
import dhbw.demo.model.*;
import dhbw.demo.text_search.FilterDocumentsByFullTextSearch;
import dhbw.demo.text_search.FullTextSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class MainController {
    private final HttpSearch httpSearch = new HttpSearch();
    private final Parser parser = new Parser();

    @Autowired
    private DocumentRepository documentRepository;


    @PostMapping(value = "/fullTextSearch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DocumentMetaDataDto>> getAllDocumentsMatchingKeyValuePairs(@RequestBody FilterQuery filterQuery) {
        boolean regExMatch = filterQuery.regExMatch;
        String searchQuery = filterQuery.searchQuery;
        Map<String, String> keyValuePairs = filterQuery.keyValuePairs;

        List<DocumentEntity> matchingDocuments = getAllDocumentsMatchingKeyValuePairs(keyValuePairs);

        MatchingDocumentsWrapper matchingDocumentsWrapper = mapMatchingDocumentsToMatchingDocumentsWrapper(matchingDocuments);

        String ids = Arrays.stream(matchingDocumentsWrapper.matchingDocuments).map(matchingDocument -> String.valueOf(matchingDocument.document_id)).collect(Collectors.joining(","));
        HttpResponse<String> response;
        TextExtractionResultWrapper textExtractionResultWrapper;
        try {
            URI uri = new URI("http://fileservice:8081/api/getTexts/" + ids);
            response = httpSearch.search(uri);
            String json = response.body();
            textExtractionResultWrapper = parser.readJsonTextExtractionResultWrapper(json);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        int[] filtered_document_ids = FilterDocumentsByFullTextSearch.filterDocumentsByFullTextSearch(regExMatch, textExtractionResultWrapper, searchQuery);

        List<DocumentEntity> filteredDocuments = documentRepository.findByIdIn(filtered_document_ids);

        List<DocumentEntity> nameMatchingDocuments = getNameMatchingDocuments(regExMatch, searchQuery);

        filteredDocuments.addAll(nameMatchingDocuments);
        List<DocumentEntity> sortedDocuments = filteredDocuments.stream().distinct().sorted(Comparator.comparing(DocumentEntity::getName)).collect(Collectors.toList());

        List<DocumentMetaDataDto> documentMetaDataDTOs = convertDocumentEntitiesToDocumentMetaDataDtoList(sortedDocuments);

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.ok().headers(responseHeader).body(documentMetaDataDTOs);
    }

    private List<DocumentEntity> getAllDocumentsMatchingKeyValuePairs(Map<String, String> keyValuePairs) {
        Predicate<DocumentEntity> matchesKeyValuePairs = document -> keyValuePairs.entrySet().stream().allMatch(keyValuePair -> document.getMetaData().stream().anyMatch(metaDate -> metaDate.getKey().equals(keyValuePair.getKey()) && metaDate.getValue().equals(keyValuePair.getValue())));

        return documentRepository.findAll().stream().filter(matchesKeyValuePairs).collect(Collectors.toList());
    }

    private MatchingDocumentsWrapper mapMatchingDocumentsToMatchingDocumentsWrapper(List<DocumentEntity> matchingDocuments) {
        List<MatchingDocument> matchingDocumentDTOs = new ArrayList<>();
        for (DocumentEntity matchingDocument : matchingDocuments) {
            MatchingDocument matchingDocumentDto = new MatchingDocument(matchingDocument.getId(), matchingDocument.getPath());
            matchingDocumentDTOs.add(matchingDocumentDto);
        }
        return new MatchingDocumentsWrapper(matchingDocumentDTOs.toArray(MatchingDocument[]::new));
    }

    private DocumentMetaDataDto convertDocumentEntityToDocumentMetaDataDto(DocumentEntity document) {
        Map<String, String> allKeyValuePairs = document.getMetaData().stream().collect(Collectors.toMap(MetaDataEntity::getKey, MetaDataEntity::getValue));
        return new DocumentMetaDataDto(document.getName(), document.getPath(), allKeyValuePairs);
    }

    private List<DocumentMetaDataDto> convertDocumentEntitiesToDocumentMetaDataDtoList(List<DocumentEntity> filteredDocuments) {
        return filteredDocuments.stream().map(this::convertDocumentEntityToDocumentMetaDataDto).collect(Collectors.toList());
    }

    private List<DocumentEntity> getNameMatchingDocuments(boolean regExMatch, String searchQuery) {
        if (regExMatch) {
            return this.documentRepository.findAll().stream().filter(document -> FullTextSearch.textMatchesRegExSubString(document.getName(), searchQuery)).collect(Collectors.toList());
        } else {
            return this.documentRepository.findAll().stream().filter(document -> FullTextSearch.textContainsLiteralSubstring(document.getName(), searchQuery)).collect(Collectors.toList());
        }
    }
}

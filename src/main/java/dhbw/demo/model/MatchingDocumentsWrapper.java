package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class MatchingDocumentsWrapper {
    @JsonProperty("matchingDocuments")
    public MatchingDocument[] matchingDocuments;

    public MatchingDocumentsWrapper(MatchingDocument[] matchingDocuments) {
        this.matchingDocuments = matchingDocuments;
    }
}

package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class MatchingDocumentsWrapper {
    @JsonProperty("matchingDocuments")
    public MatchingDocument[] matchingDocuments;

    public MatchingDocumentsWrapper(MatchingDocument[] matchingDocuments) {
        this.matchingDocuments = matchingDocuments;
    }

    public MatchingDocumentsWrapper() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchingDocumentsWrapper)) return false;
        MatchingDocumentsWrapper that = (MatchingDocumentsWrapper) o;
        return Arrays.equals(matchingDocuments, that.matchingDocuments);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matchingDocuments);
    }
}

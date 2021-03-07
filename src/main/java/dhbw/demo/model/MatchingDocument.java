package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class MatchingDocument {
    @JsonProperty("document_id")
    public int document_id;

    @JsonProperty("path")
    public String path;

    public MatchingDocument(int document_id, String path) {
        this.document_id = document_id;
        this.path = path;
    }

    public MatchingDocument() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchingDocument)) return false;
        MatchingDocument that = (MatchingDocument) o;
        return document_id == that.document_id && path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document_id, path);
    }
}

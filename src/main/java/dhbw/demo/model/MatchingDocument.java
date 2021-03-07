package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class MatchingDocument {
    @JsonProperty("document_id")
    public int document_id;

    @JsonProperty("path")
    public String path;

    public MatchingDocument(int document_id, String path) {
        this.document_id = document_id;
        this.path = path;
    }
}

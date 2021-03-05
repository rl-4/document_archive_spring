package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class TextExtractionResult {

    @JsonProperty("document_id")
    public int document_id;

    @JsonProperty("text")
    public String text;

}

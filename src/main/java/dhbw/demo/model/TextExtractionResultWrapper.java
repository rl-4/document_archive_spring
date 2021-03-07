package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class TextExtractionResultWrapper {
    @JsonProperty("textExtractionResults")
    public TextExtractionResult[] textExtractionResults;

    public TextExtractionResultWrapper(TextExtractionResult[] textExtractionResults) {
        this.textExtractionResults = textExtractionResults;
    }
}

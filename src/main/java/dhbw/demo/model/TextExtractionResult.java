package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class TextExtractionResult {

    @JsonProperty("document_id")
    public int document_id;

    @JsonProperty("extractedText")
    public String extractedText;

    @JsonProperty("extractedCells")
    public String[] extractedCells;

    public TextExtractionResult(int document_id, String extractedText, String[] extractedCells) {
        this.document_id = document_id;
        this.extractedText = extractedText;
        this.extractedCells = extractedCells;
    }

    public TextExtractionResult(int document_id, String extractedText) {
        this.document_id = document_id;
        this.extractedText = extractedText;
    }

    public TextExtractionResult(int document_id, String[] extractedCells) {
        this.document_id = document_id;
        this.extractedCells = extractedCells;
    }
}

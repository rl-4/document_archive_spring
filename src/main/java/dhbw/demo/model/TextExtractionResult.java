package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

public class TextExtractionResult {

    @JsonProperty("document_id")
    public int document_id;

    @JsonProperty("extractedText")
    public String extractedText;

    @JsonProperty("extractedCells")
    public String[] extractedCells;

    public TextExtractionResult(int document_id, String extractedText) {
        this.document_id = document_id;
        this.extractedText = extractedText;
    }

    public TextExtractionResult(int document_id, String[] extractedCells) {
        this.document_id = document_id;
        this.extractedCells = extractedCells;
    }

    public TextExtractionResult() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextExtractionResult)) return false;
        TextExtractionResult that = (TextExtractionResult) o;
        return document_id == that.document_id && Objects.equals(extractedText, that.extractedText) && Arrays.equals(extractedCells, that.extractedCells);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(document_id, extractedText);
        result = 31 * result + Arrays.hashCode(extractedCells);
        return result;
    }
}

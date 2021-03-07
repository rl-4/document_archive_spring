package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class TextExtractionResultWrapper {
    @JsonProperty("textExtractionResults")
    public TextExtractionResult[] textExtractionResults;

    public TextExtractionResultWrapper(TextExtractionResult[] textExtractionResults) {
        this.textExtractionResults = textExtractionResults;
    }

    public TextExtractionResultWrapper(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextExtractionResultWrapper)) return false;
        TextExtractionResultWrapper that = (TextExtractionResultWrapper) o;
        return Arrays.equals(textExtractionResults, that.textExtractionResults);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(textExtractionResults);
    }
}

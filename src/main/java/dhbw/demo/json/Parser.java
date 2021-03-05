package dhbw.demo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dhbw.demo.model.TextExtractionResult;

public class Parser {
    private ObjectMapper objectMapper = new ObjectMapper();

    public TextExtractionResult parse(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, TextExtractionResult.class);
    }
}
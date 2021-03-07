package dhbw.demo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dhbw.demo.model.FilterQuery;
import dhbw.demo.model.MatchingDocumentsWrapper;
import dhbw.demo.model.TextExtractionResultWrapper;

public class Parser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TextExtractionResultWrapper readJsonTextExtractionResultWrapper(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, TextExtractionResultWrapper.class);
    }

    public MatchingDocumentsWrapper readJsonMatchingDocumentsWrapper(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, MatchingDocumentsWrapper.class);
    }

    public String writeJsonTextExtractionResultWrapper(TextExtractionResultWrapper textExtractionResultWrapper) throws JsonProcessingException {
        String test = objectMapper.writeValueAsString(textExtractionResultWrapper);
        System.out.println(test);
        return test;
    }

    public String writeJsonMatchingDocumentsWrapper(MatchingDocumentsWrapper matchingDocumentsWrapper) throws JsonProcessingException {
        String test = objectMapper.writeValueAsString(matchingDocumentsWrapper);
        System.out.println(test);
        return test;
    }

    public String filterQueryToJson(FilterQuery filterQuery) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(filterQuery);
        System.out.println(json);
        return json;
    }

    public FilterQuery jsonToFilterQuery(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, FilterQuery.class);
    }
}
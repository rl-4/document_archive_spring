package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class DocumentMetaDataDto {
    @JsonProperty
    private String name;
    @JsonProperty
    private String path;
    @JsonProperty
    private Map<String, String> metaData;

    public DocumentMetaDataDto(){

    }

    public DocumentMetaDataDto(String name, String path, Map<String, String> metaData) {
        this.name = name;
        this.path = path;
        this.metaData = metaData;
    }
}

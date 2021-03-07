package dhbw.demo.model;

import java.util.Map;

public class DocumentMetaDataDto {
    private String name;

    private String path;

    private Map<String, String> metaData;

    public DocumentMetaDataDto(String name, String path, Map<String, String> metaData) {
        this.name = name;
        this.path = path;
        this.metaData = metaData;
    }
}

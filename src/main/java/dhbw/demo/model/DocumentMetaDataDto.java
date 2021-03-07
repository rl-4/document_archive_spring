package dhbw.demo.model;

import java.util.Map;

public class DocumentMetaDataDto {
    private final String name;

    private final String path;

    private final Map<String, String> metaData;

    public DocumentMetaDataDto(String name, String path, Map<String, String> metaData) {
        this.name = name;
        this.path = path;
        this.metaData = metaData;
    }
}

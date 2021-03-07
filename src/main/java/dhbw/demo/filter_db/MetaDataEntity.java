package dhbw.demo.filter_db;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "metadata")
public class MetaDataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentEntity document;

    public MetaDataEntity() {

    }

    public MetaDataEntity(String key, String value, DocumentEntity document) {
        this.key = key;
        this.value = value;
        this.document = document;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "MetaDataEntity{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
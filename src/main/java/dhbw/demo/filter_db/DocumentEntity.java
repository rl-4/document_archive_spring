package dhbw.demo.filter_db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "documents")
public class DocumentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path", nullable = false)
    private String path;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private Set<MetaDataEntity> metaData = new HashSet<>();

    public DocumentEntity() {
    }

    public DocumentEntity(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<MetaDataEntity> getMetaData() {
        return metaData;
    }

    public void setMetaData(Set<MetaDataEntity> metaData) {
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        return "DocumentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

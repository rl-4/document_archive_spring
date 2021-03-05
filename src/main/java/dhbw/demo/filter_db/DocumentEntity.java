package dhbw.demo.filter_db;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;

    @Column(name = "name")
    public String name;

    /*
    @Column(name = "documentFile")
    public File documentFile;

     */
}

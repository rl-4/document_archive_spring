package dhbw.demo.filter_db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentArchiveRepository extends JpaRepository<DocumentEntity, Integer> {
    List<DocumentEntity> findByNameContaining(String name);
}

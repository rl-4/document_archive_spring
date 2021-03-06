package dhbw.demo.filter_db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetaDataRepository extends JpaRepository<MetaDataEntity, Integer> {
    List<MetaDataEntity> findByKeyAndValue(String key, String value);
}

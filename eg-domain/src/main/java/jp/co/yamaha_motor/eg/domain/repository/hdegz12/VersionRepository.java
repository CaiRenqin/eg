package jp.co.yamaha_motor.eg.domain.repository.hdegz12;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.yamaha_motor.eg.domain.entity.VersionEntity;
import jp.co.yamaha_motor.eg.domain.entity.VersionId;

@Repository
public interface VersionRepository extends JpaRepository<VersionEntity, VersionId> {

    @Query("SELECT v FROM VersionEntity v ORDER BY v.lastUpdated DESC Limit 1")
    VersionEntity findFirstByOrderByLastUpdatedDesc();
}
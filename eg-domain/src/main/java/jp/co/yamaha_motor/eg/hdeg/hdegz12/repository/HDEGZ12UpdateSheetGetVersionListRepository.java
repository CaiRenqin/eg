package jp.co.yamaha_motor.eg.hdeg.hdegz12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.yamaha_motor.eg.common.entity.VersionEntity;
import jp.co.yamaha_motor.eg.common.entity.VersionId;

@Repository
public interface HDEGZ12UpdateSheetGetVersionListRepository extends JpaRepository<VersionEntity, VersionId> {

    @Query("SELECT v FROM VersionEntity v ORDER BY v.lastUpdated DESC LIMIT 1")
    VersionEntity findFirstByOrderByLastUpdatedDesc();
}
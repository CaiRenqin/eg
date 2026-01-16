package jp.co.yamaha_motor.hdeg.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import jp.co.yamaha_motor.hdeg.common.entity.VersionEntity;
import jp.co.yamaha_motor.hdeg.common.entity.VersionId;

@Repository
public interface VersionRepository extends JpaRepository<VersionEntity, VersionId> {

    /**
     * 最新のバージョン情報を取得する
     *
     * @return 最新のVersionEntity
     */
    @Query("SELECT v FROM VersionEntity v ORDER BY v.lastUpdated DESC LIMIT 1")
    Optional<VersionEntity> getVersionInfo();
}
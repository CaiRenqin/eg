package jp.co.yamaha_motor.hdeg.hdeg.hdegz12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.yamaha_motor.hdeg.common.entity.VersionEntity;
import jp.co.yamaha_motor.hdeg.common.entity.VersionId;

@Repository
public interface HDEGZ12UpdateSheetGetVersionListRepository extends JpaRepository<VersionEntity, VersionId> {

    /**
     * 最新のバージョン情報を取得する
     *
     * @return 最新のVersionEntity
     */
    @Query("SELECT v FROM VersionEntity v ORDER BY v.lastUpdated DESC LIMIT 1")
    VersionEntity findFirstByOrderByLastUpdatedDesc();
}
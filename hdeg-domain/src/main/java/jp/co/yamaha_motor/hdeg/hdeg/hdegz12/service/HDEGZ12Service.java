package jp.co.yamaha_motor.hdeg.hdeg.hdegz12.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.yamaha_motor.hdeg.common.entity.VersionEntity;
import jp.co.yamaha_motor.hdeg.common.repository.VersionRepository;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz12.dto.HDEGZ12ResultDTO;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HDEGZ12Service {

    private final VersionRepository versionRepository;

    /**
     * 最新バージョンを取得する
     *
     * @return 最新バージョンのDTO
     */
    public HDEGZ12ResultDTO getLatestVersion() {
        VersionEntity entity = versionRepository.getVersionInfo()
                .orElse(new VersionEntity());
        return convertEntityToDTO(entity);
    }

    /**
     * EntityをDTOに変換する
     *
     * @param entity 変換元のエンティティ
     * @return 変換後のDTO
     */
    private HDEGZ12ResultDTO convertEntityToDTO(VersionEntity entity) {
        HDEGZ12ResultDTO dto = new HDEGZ12ResultDTO();

        if (entity.getVersionId() == null) {
            return dto;
        }

        dto.setMajorVer(entity.getVersionId().getMajorVer());
        dto.setMinorVer(entity.getVersionId().getMinorVer());
        dto.setReleaseVer(entity.getVersionId().getReleaseVer());

        if (entity.getLastUpdated() != null) {
            String formatted = entity.getLastUpdated().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
            dto.setUpdateDate(new HDEGZ12ResultDTO.SqlTimestamp(formatted));
        }

        return dto;
    }
}
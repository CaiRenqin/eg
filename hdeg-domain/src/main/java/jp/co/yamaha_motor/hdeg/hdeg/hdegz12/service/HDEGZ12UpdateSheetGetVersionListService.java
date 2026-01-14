package jp.co.yamaha_motor.hdeg.hdeg.hdegz12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.yamaha_motor.hdeg.common.entity.VersionEntity;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz12.dto.HDEGZ12ResultDTO;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz12.repository.HDEGZ12UpdateSheetGetVersionListRepository;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HDEGZ12UpdateSheetGetVersionListService {

    private final HDEGZ12UpdateSheetGetVersionListRepository hdegz12Repository;

    /**
     * 最新バージョンを取得する
     *
     * @return 最新バージョンのDTO
     */
    public HDEGZ12ResultDTO getLatestVersion() {
        VersionEntity entity = hdegz12Repository.findFirstByOrderByLastUpdatedDesc();
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

        dto.setMajorVer(entity.getVersionId().getMajorVer());
        dto.setMinorVer(entity.getVersionId().getMinorVer());
        dto.setReleaseVer(entity.getVersionId().getReleaseVer());

        String formatted = entity.getLastUpdated().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        dto.setUpdateDate(new HDEGZ12ResultDTO.SqlTimestamp(formatted));

        return dto;
    }
}
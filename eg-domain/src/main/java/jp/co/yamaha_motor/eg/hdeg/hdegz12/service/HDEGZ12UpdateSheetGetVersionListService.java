package jp.co.yamaha_motor.eg.hdeg.hdegz12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.yamaha_motor.eg.common.entity.VersionEntity;
import jp.co.yamaha_motor.eg.hdeg.hdegz12.dto.VersionResponseDTO;
import jp.co.yamaha_motor.eg.hdeg.hdegz12.repository.HDEGZ12UpdateSheetGetVersionListRepository;

import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HDEGZ12UpdateSheetGetVersionListService {

    private final HDEGZ12UpdateSheetGetVersionListRepository myRepository;

    public List<VersionResponseDTO> getLatestVersion() {
        VersionEntity entity = myRepository.findFirstByOrderByLastUpdatedDesc();
        List<VersionEntity> entityList = Collections.singletonList(entity);

        return entityList.stream().map(this::convertEntityToDTO).toList();
    }

    private VersionResponseDTO convertEntityToDTO(VersionEntity entity) {
        VersionResponseDTO dto = new VersionResponseDTO();

        dto.setMajorVer(entity.getVersionId().getMajorVer());
        dto.setMinorVer(entity.getVersionId().getMinorVer());
        dto.setReleaseVer(entity.getVersionId().getReleaseVer());

        if (entity.getLastUpdated() != null) {
            String formatted = entity.getLastUpdated().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
            dto.setUpdateDate(new VersionResponseDTO.SqlTimestamp(formatted));
        }

        return dto;
    }
}
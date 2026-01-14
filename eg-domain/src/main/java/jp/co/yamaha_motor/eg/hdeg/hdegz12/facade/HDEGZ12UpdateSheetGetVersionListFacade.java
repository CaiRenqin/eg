package jp.co.yamaha_motor.eg.hdeg.hdegz12.facade;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.yamaha_motor.eg.hdeg.hdegz12.dto.VersionListResponseDTO;
import jp.co.yamaha_motor.eg.hdeg.hdegz12.dto.VersionResponseDTO;
import jp.co.yamaha_motor.eg.hdeg.hdegz12.service.HDEGZ12UpdateSheetGetVersionListService;

@Component
@RequiredArgsConstructor
public class HDEGZ12UpdateSheetGetVersionListFacade {

    private final HDEGZ12UpdateSheetGetVersionListService myService;

    public VersionListResponseDTO getLatestVersion() {
        List<VersionResponseDTO> versionList = myService.getLatestVersion();
        return new VersionListResponseDTO(versionList);
    }

}

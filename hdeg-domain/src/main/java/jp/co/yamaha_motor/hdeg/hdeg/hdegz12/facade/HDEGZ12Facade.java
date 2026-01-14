package jp.co.yamaha_motor.hdeg.hdeg.hdegz12.facade;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import jp.co.yamaha_motor.hdeg.hdeg.hdegz12.dto.HDEGZ12ResultDTO;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz12.service.HDEGZ12UpdateSheetGetVersionListService;

@Component
@RequiredArgsConstructor
public class HDEGZ12Facade {

    private final HDEGZ12UpdateSheetGetVersionListService hdegz12Service;

    public HDEGZ12ResultDTO getLatestVersion() {
        return hdegz12Service.getLatestVersion();
    }

}

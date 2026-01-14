package jp.co.yamaha_motor.hdeg.web.app.controller.hdegz12;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.yamaha_motor.hdeg.common.util.XMLUtil;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz12.dto.HDEGZ12ResultDTO;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz12.facade.HDEGZ12Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.MediaType;

@Tag(name = "VersionController", description = "バージョン情報検索")
@RestController
@RequestMapping("/hdegz12")
@RequiredArgsConstructor
public class HDEGZ12Controller {

    private final HDEGZ12Facade hdegz12Facade;

    /**
     * バージョン情報検索
     *
     * @param response HTTPレスポンス
     * @return XML形式のバージョン情報
     */
    @Operation(summary = "バージョン情報検索", description = "バージョン情報を取得する(HDEGZ12-P001)")
    @PostMapping(value = "/HDEGZ12UpdateSheetGetVersionList", produces = MediaType.APPLICATION_XML_VALUE)
    public String getVersionList(HttpServletResponse response) {
        HDEGZ12ResultDTO singleVersion = hdegz12Facade.getLatestVersion();
        List<HDEGZ12ResultDTO> versionList = new ArrayList<>(List.of(singleVersion));
        return XMLUtil.convDao2Xml(versionList, HDEGZ12ResultDTO.class);
    }
}

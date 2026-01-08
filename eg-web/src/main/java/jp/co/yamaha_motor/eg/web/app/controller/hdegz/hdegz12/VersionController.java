package jp.co.yamaha_motor.eg.web.app.controller.hdegz.hdegz12;

import com.ymsl.solid.web.restful.json.model.RestProcessAware;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.yamaha_motor.eg.dto.hdegz12.VersionResponseDTO;
import jp.co.yamaha_motor.eg.common.facade.hdegz12.VersionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jp.co.yamaha_motor.eg.dto.hdegz12.VersionListResponseDTO;

import java.util.List;

import org.springframework.http.MediaType;

@Tag(name = "VersionController", description = "バージョン情報検索")
@RestController
@RequestMapping("/hdegz12")
@RequiredArgsConstructor
public class VersionController implements RestProcessAware {

    private final VersionFacade versionFacade;

    @Operation(summary = "バージョン情報検索", description = "バージョン情報を取得する(HDEGZ12-P001)")
    @PostMapping(value = "/HDEGZ12UpdateSheetGetVersionList", produces = MediaType.APPLICATION_XML_VALUE)
    public VersionListResponseDTO getVersionList(HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_XML_VALUE);
        List<VersionResponseDTO> versionList = versionFacade.getLatestVersion();
        return new VersionListResponseDTO(versionList);
    }

    @PostMapping("/test1")
    public String postMethodName() {
        return "success";
    }
}

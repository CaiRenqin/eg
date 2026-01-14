package jp.co.yamaha_motor.eg.web.app.controller.hdegz12;

import com.ymsl.solid.web.restful.json.model.RestProcessAware;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.yamaha_motor.eg.hdeg.hdegz12.dto.VersionListResponseDTO;
import jp.co.yamaha_motor.eg.hdeg.hdegz12.facade.HDEGZ12UpdateSheetGetVersionListFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

@Tag(name = "VersionController", description = "バージョン情報検索")
@RestController
@RequestMapping("/hdegz12")
@RequiredArgsConstructor
public class HDEGZ12UpdateSheetGetVersionListController implements RestProcessAware {

    private final HDEGZ12UpdateSheetGetVersionListFacade myFacade;

    @Operation(summary = "バージョン情報検索", description = "バージョン情報を取得する(HDEGZ12-P001)")
    @PostMapping(value = "/HDEGZ12UpdateSheetGetVersionList", produces = MediaType.APPLICATION_XML_VALUE)
    public VersionListResponseDTO getVersionList(HttpServletResponse response) {
        return myFacade.getLatestVersion();
    }

    @PostMapping("/test1")
    public String postMethodName() {
        return "success";
    }
}

package jp.co.yamaha_motor.hdeg.web.app.controller.hdegz13;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz13.dto.HDEGZ13ResultDTO;
import jp.co.yamaha_motor.hdeg.common.util.XMLUtil;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz13.dto.HDEGZ13ParameterDTO;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz13.facade.HDEGZ13Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;

@Tag(name = "HDEGZ13UpdateSheetNumberingController", description = "採番処理")
@RestController
@RequestMapping("/hdegz13")
@RequiredArgsConstructor
public class HDEGZ13Controller {

    private final HDEGZ13Facade hdegz13Facade;

    /**
     * 採番処理
     *
     * @param request  リクエストDTO
     * @param response HTTPレスポンス
     * @return XML形式の結果
     */
    @Operation(summary = "採番処理", description = "採番処理(HDEGZ13-P001)")
    @PostMapping(value = "/HDEGZ13UpdateSheetNumbering", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public String updateSheetNumbering(
            @ModelAttribute HDEGZ13ParameterDTO request,
            HttpServletResponse response) {
        HDEGZ13ResultDTO numbering = hdegz13Facade.updateSheetNumbering(request);
        List<HDEGZ13ResultDTO> numberingList = new ArrayList<>(List.of(numbering));
        return XMLUtil.convDao2Xml(numberingList, HDEGZ13ResultDTO.class);
    }
}

package jp.co.yamaha_motor.eg.web.app.controller.hdegz13;

import com.ymsl.solid.web.restful.json.model.RestProcessAware;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.yamaha_motor.eg.hdeg.hdegz13.dto.HDEGZ13UpdateSheetNumberingRespondDTO;
import jp.co.yamaha_motor.eg.hdeg.hdegz13.dto.HDEGZ13UpdateSheetNumberingRequestDTO;
import jp.co.yamaha_motor.eg.hdeg.hdegz13.facade.HDEGZ13UpdateSheetNumberingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

@Tag(name = "HDEGZ13UpdateSheetNumberingController", description = "採番処理")
@RestController
@RequestMapping("/hdegz13")
@RequiredArgsConstructor
public class HDEGZ13UpdateSheetNumberingController implements RestProcessAware {

    private final HDEGZ13UpdateSheetNumberingFacade myFacade;

    @Operation(summary = "採番処理", description = "採番処理(HDEGZ13-P001)")
    @PostMapping(value = "/HDEGZ13UpdateSheetNumbering", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public HDEGZ13UpdateSheetNumberingRespondDTO updateSheetNumbering(
            @ModelAttribute HDEGZ13UpdateSheetNumberingRequestDTO request,
            HttpServletResponse response) {
        String tableName = request.getScreenModel().getTableName();
        String numberingId = myFacade.updateSheetNumbering(tableName);
        return new HDEGZ13UpdateSheetNumberingRespondDTO(numberingId);
    }
}

package jp.co.yamaha_motor.eg.hdeg.hdegz13.facade;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.yamaha_motor.eg.hdeg.hdegz13.dto.HDEGZ13UpdateSheetNumberingRequestDTO;
import jp.co.yamaha_motor.eg.hdeg.hdegz13.dto.HDEGZ13UpdateSheetNumberingRespondDTO;
import jp.co.yamaha_motor.eg.hdeg.hdegz13.service.HDEGZ13UpdateSheetNumberingService;

@Component
@RequiredArgsConstructor
public class HDEGZ13UpdateSheetNumberingFacade {
    private final HDEGZ13UpdateSheetNumberingService myService;

    public HDEGZ13UpdateSheetNumberingRespondDTO updateSheetNumbering(
            @ModelAttribute HDEGZ13UpdateSheetNumberingRequestDTO request) {
        String tableName = request.getScreenModel().getTableName();
        String numberingId = myService.updateNumberingBySave(tableName);
        return new HDEGZ13UpdateSheetNumberingRespondDTO(numberingId);
    }
}

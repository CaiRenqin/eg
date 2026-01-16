package jp.co.yamaha_motor.hdeg.hdeg.hdegz13.facade;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.yamaha_motor.hdeg.hdeg.hdegz13.dto.HDEGZ13RequestDTO;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz13.dto.HDEGZ13ResultDTO;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz13.service.HDEGZ13Service;

@Component
@RequiredArgsConstructor
public class HDEGZ13Facade {
    private final HDEGZ13Service hdegz13Service;

    private String numberingId;

    /**
     * 採番処理
     *
     * @param request リクエストDTO
     * @return 結果DTO
     */
    public HDEGZ13ResultDTO updateSheetNumbering(
            @ModelAttribute HDEGZ13RequestDTO request) {
        String tableName = request.getScreenModel().getTableName();

        if (tableName == null || tableName.isEmpty()) {
            numberingId = "";
        } else {
            numberingId = hdegz13Service.updateNumberingAndSave(tableName);
        }

        return new HDEGZ13ResultDTO(numberingId);
    }
}

package jp.co.yamaha_motor.hdeg.hdeg.hdegz13.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HDEGZ13ParameterDTO {
    private ScreenModel screenModel;

    @Data
    public static class ScreenModel {
        private String tableName;
    }
}

package jp.co.yamaha_motor.hdeg.hdeg.hdegz13.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.yamaha_motor.hdeg.common.entity.NumberingEntity;
import jp.co.yamaha_motor.hdeg.hdeg.hdegz13.repository.HDEGZ13UpdateSheetNumberingRepository;

import java.util.Map;
import java.util.HashMap;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HDEGZ13UpdateSheetNumberingService {
    private final HDEGZ13UpdateSheetNumberingRepository hdegz13Repository;

    private static final Map<String, Integer> TABLE_NAME_MAP = new HashMap<>();
    // TABLE_NAMEに出現する可能性のある値
    static {
        TABLE_NAME_MAP.put("PRODUCT", 6);
        TABLE_NAME_MAP.put("COMPONENT_DATA", 6);
        TABLE_NAME_MAP.put("DATA", 7);
        TABLE_NAME_MAP.put("PART", 7);
        TABLE_NAME_MAP.put("MODEL", 4);
    }

    /**
     * 番号をゼロ埋めする
     *
     * @param num 番号
     * @param map TABLE_NAME
     * @return ゼロ埋めされた番号
     */
    private String fillZero(int num, int map) {
        String formatPattern = String.format("%%0%dd", map);
        return String.format(formatPattern, num);
    }

    /**
     * 保存時に番号を更新する
     *
     * @param tableName テーブル名
     * @return 更新後の番号
     */
    @Transactional
    public String updateNumberingBySave(String tableName) {
        NumberingEntity numbering = hdegz13Repository.findByTableName(tableName);
        String fullNum = fillZero(numbering.getNum(), TABLE_NAME_MAP.get(tableName));

        numbering.setNum(numbering.getNum() + 1);
        hdegz13Repository.save(numbering);

        return numbering.getPrefix() + fullNum;
    }
}

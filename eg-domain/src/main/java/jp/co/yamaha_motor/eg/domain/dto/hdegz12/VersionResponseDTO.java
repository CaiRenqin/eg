package jp.co.yamaha_motor.eg.domain.dto.hdegz12;

import lombok.Data;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@Data
public class VersionResponseDTO {
    private String majorVer;
    private String minorVer;
    private String releaseVer;
    private SqlTimestamp updateDate;

    public static class SqlTimestamp {
        @XmlAttribute(name = "class")
        public static final String CLASSNAME = "sql-timestamp";

        @XmlValue
        public String value;

        public SqlTimestamp() {
        }

        public SqlTimestamp(String value) {
            this.value = value;
        }
    }
}

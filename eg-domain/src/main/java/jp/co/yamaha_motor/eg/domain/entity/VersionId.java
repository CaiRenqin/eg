package jp.co.yamaha_motor.eg.domain.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "majorVer", "minorVer", "releaseVer" })
public class VersionId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String majorVer;
    private String minorVer;
    private String releaseVer;
}
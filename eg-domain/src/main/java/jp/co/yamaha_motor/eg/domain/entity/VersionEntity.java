package jp.co.yamaha_motor.eg.domain.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "version", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class VersionEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "majorVer", column = @Column(name = "major_ver", nullable = false, length = 5))
    @AttributeOverride(name = "minorVer", column = @Column(name = "minor_ver", nullable = false, length = 5))
    @AttributeOverride(name = "releaseVer", column = @Column(name = "release_ver", nullable = false, length = 5))
    private VersionId versionId;

    @Column(name = "bugfix_ver", nullable = true, length = 5)
    private String bugfixVer;
}
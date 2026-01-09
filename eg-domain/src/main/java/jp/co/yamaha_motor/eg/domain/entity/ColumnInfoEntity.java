package jp.co.yamaha_motor.eg.domain.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "column_info", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ColumnInfoEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "tableId", column = @Column(name = "table_id", nullable = false, length = 10))
    @AttributeOverride(name = "columnId", column = @Column(name = "column_id", nullable = false, length = 5))
    private ColumnInfoId columnInfoId;

    @Column(name = "column_nm_j", nullable = true, length = 100)
    private String columnNmJ;

    @Column(name = "column_nm_e", nullable = true, length = 50)
    private String columnNmE;

    @Column(name = "column_nm_j2", nullable = true, length = 30)
    private String columnNmJ2;

    @Column(name = "column_kind1", nullable = true, length = 150)
    private String columnKind1;

    @Column(name = "column_kind2", nullable = true, length = 200)
    private String columnKind2;

    @Column(name = "measure_kind", nullable = true, length = 20)
    private String measureKind;

    @Column(name = "column_unit", nullable = true, length = 20)
    private String columnUnit;

    @Column(name = "column_type", nullable = true, length = 20)
    private String columnType;

    @Column(name = "column_size", nullable = true, precision = 10, scale = 0)
    private Integer columnSize;

    @Column(name = "master_table_id", nullable = true, length = 10)
    private String masterTableId;

    @Column(name = "data_type", nullable = true, precision = 1, scale = 0)
    private Integer dataType;

    @Column(name = "disp_flg", nullable = true, precision = 1, scale = 0)
    private Integer dispFlg;

    @Column(name = "sort_id", nullable = true, precision = 4, scale = 0)
    private Integer sortId;

    @Column(name = "enable_flg", nullable = true, precision = 1, scale = 0)
    private Integer enableFlg;
}

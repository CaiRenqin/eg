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
@Table(name = "component", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ComponentEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "compId", column = @Column(name = "comp_id", nullable = false, length = 10))
    @AttributeOverride(name = "compTableId", column = @Column(name = "comp_table_id", nullable = false, length = 5))
    private ComponentId componentId;

    @Column(name = "parent_id", nullable = true, length = 10)
    private String parentId;

    @Column(name = "name_id", nullable = true, length = 10)
    private String nameId;

    @Column(name = "table_id", nullable = true, length = 10)
    private String tableId;

    @Column(name = "ref_table_id", nullable = true, length = 10)
    private String refTableId;

    @Column(name = "sort_id", nullable = true, precision = 1, scale = 0)
    private Integer sortId;

    @Column(name = "column_nm_j", nullable = true, precision = 1, scale = 0)
    private Integer columnNmJ;
}
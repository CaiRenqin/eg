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
@Table(name = "component_data", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ComponentDataEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "compId", column = @Column(name = "comp_data_id", nullable = false, length = 20))
    @AttributeOverride(name = "compTableId", column = @Column(name = "comp_table_id", nullable = false, length = 5))
    private ComponentDataId componentDataId;

    @Column(name = "data_id", nullable = true, length = 10)
    private String parentId;

    @Column(name = "ref_comp_data_id", nullable = true, length = 20)
    private String nameId;
}
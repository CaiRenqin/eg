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
@Table(name = "comp_data_lnk", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CompDataLnkEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "productId", column = @Column(name = "product_id", nullable = false, length = 20))
    @AttributeOverride(name = "compId", column = @Column(name = "comp_id", nullable = false, length = 10))
    @AttributeOverride(name = "compDataId", column = @Column(name = "comp_data_id", nullable = false, length = 20))
    private CompDataLnkId compDataLnkId;
}
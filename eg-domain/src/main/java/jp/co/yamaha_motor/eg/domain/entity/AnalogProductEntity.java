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
@Table(name = "analog_product", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class AnalogProductEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "productId", column = @Column(name = "product_id", nullable = false, length = 20)) // プロダクトID
    @AttributeOverride(name = "anlProductId", column = @Column(name = "anl_product_id", nullable = false, length = 20)) // 類似プロダクトID
    private AnalogProductId analogProductId;

    @Column(name = "sort_id", nullable = true, precision = 4, scale = 0)
    private Integer sortId; // 表示順
}

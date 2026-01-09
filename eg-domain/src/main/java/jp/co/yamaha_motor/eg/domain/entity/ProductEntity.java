package jp.co.yamaha_motor.eg.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProductEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "product_id", nullable = false, length = 20)
    private String productId;// プロダクトID

    @Column(name = "comp_data_id", nullable = false, length = 20)
    private String compDataId; // 構成データID

    @Column(name = "model_id", nullable = true, length = 10)
    private String modelId; // モデルID

    @Column(name = "phase", nullable = true, length = 20)
    private String phase; // 次元

    @Column(name = "rev", nullable = true, length = 10)
    private String rev; // リビジョン

    @Column(name = "comments", nullable = true, length = 100)
    private String comments; // コメント

    @Column(name = "active", nullable = true, precision = 1, scale = 0)
    private Integer active; // アクティブ

    @Column(name = "product_type", nullable = true, precision = 1, scale = 0)
    private Integer productType; // タイプ

    @Column(name = "base_prd_id", nullable = true, length = 20)
    private String basePrdId; // 元プロダクトID

    @Column(name = "planning", nullable = true, precision = 1, scale = 0)
    private Integer planning; // 検討中

    @Column(name = "specs_last_update_user", nullable = true, length = 10)
    private String specsLastUpdateUser; // 諸元最終更新者

    @Column(name = "specs_last_update_date", nullable = true)
    private LocalDateTime specsLastUpdateDate; // 諸元最終更新日
}

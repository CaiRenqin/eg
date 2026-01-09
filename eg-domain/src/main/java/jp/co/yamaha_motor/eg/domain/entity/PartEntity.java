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
@Table(name = "part", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PartEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "part_id", nullable = false, length = 20)
    private String partId;// 部品ID

    @Column(name = "part_no", nullable = true, length = 40)
    private String partNo; // 部品番号

    @Column(name = "rev", nullable = true, length = 2)
    private String rev; // リビジョン

    @Column(name = "planning", nullable = true, precision = 1, scale = 0)
    private Integer planning; // 検討中

    @Column(name = "active", nullable = true, precision = 1, scale = 0)
    private Integer active; // アクティブ

    @Column(name = "base_part_id", nullable = true, length = 20)
    private String basePartId; // コピー元部品ID

    @Column(name = "mass", nullable = true, length = 255)
    private String mass; // 質量

    @Column(name = "massmethod_id", nullable = true, length = 3)
    private String massmethodId; // 質量種別ID

    @Column(name = "model4newparts", nullable = true, length = 10)
    private String model4newparts; // 新作時登録型式

    @Column(name = "phase4newparts", nullable = true, length = 3)
    private String phase4newparts; // 新作時次元

    @Column(name = "specs_last_update_user", nullable = true, length = 10)
    private String specsLastUpdateUser; // 諸元最終更新者

    @Column(name = "specs_last_update_date", nullable = true)
    private LocalDateTime specsLastUpdateDate; // 諸元最終更新日
}

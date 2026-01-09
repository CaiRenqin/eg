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
@Table(name = "model", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ModelEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "model_id", nullable = false, length = 20)
    private String modelId;// モデルID

    @Column(name = "model", nullable = true, length = 10)
    private String model; // 登録型式

    @Column(name = "model_name", nullable = true, length = 12)
    private String modelName; // 機種名

    @Column(name = "model_year", nullable = true, length = 4)
    private String modelYear; // モデル年度

    @Column(name = "project_no", nullable = true, length = 50)
    private String projectNo; // プロジェクトNo

    @Column(name = "production_date", nullable = true, length = 10)
    private String productionDate; // 立上年月

    @Column(name = "kickoff_date", nullable = true, length = 10)
    private String kickoffDate; // KickOff年度

    @Column(name = "plan_code", nullable = true, length = 10)
    private String planCode; // 計画コード

    @Column(name = "product_category", nullable = true, length = 3)
    private String productCategory; // 商品分類ID

    @Column(name = "model_category", nullable = true, length = 3)
    private String modelCategory; // モデルカテゴリID

    @Column(name = "model_family", nullable = true, length = 20)
    private String modelFamily; // 機種ファミリー

    @Column(name = "eg_family", nullable = true, length = 20)
    private String egFamily; // EGファミリー

    @Column(name = "common_name", nullable = true, length = 20)
    private String commonName; // 機種総称

    @Column(name = "maker_id", nullable = true, length = 3)
    private String makerId; // メーカーID

    @Column(name = "maker_other_nm", nullable = true, length = 20)
    private String makerOtherNm; // その他メーカー名称

    @Column(name = "maker_other_flg", nullable = true, precision = 1, scale = 0)
    private Integer makerOtherFlg; // 他社フラグ

    @Column(name = "development_flg", nullable = true, precision = 1, scale = 0)
    private Integer developmentFlg; // 開発中フラグ

    @Column(name = "model_summary", nullable = true, length = 1000)
    private String modelSummary; // 備考

    @Column(name = "riding_capacity", nullable = true, length = 10)
    private String ridingCapacity; // 乗車定員

    @Column(name = "selling_price", nullable = true, length = 10)
    private String sellingPrice; // 販売価格

    @Column(name = "price_unit", nullable = true, length = 10)
    private String priceUnit; // 通貨

    @Column(name = "delivery_place", nullable = true, length = 200)
    private String deliveryPlace; // 納入場所

    @Column(name = "model_disp", nullable = true, length = 100)
    private String modelDisp; // 表示モデル名

    @Column(name = "oy_plan_cd", nullable = true, length = 10)
    private String oyPlanCd; // 開発代表計画CD

    @Column(name = "product_category_nm", nullable = true, length = 20)
    private String productCategoryNm; // 商品分類名称

    @Column(name = "dvlp_rnk_cd", nullable = true, length = 2)
    private String dvlpRnkCd; // 開発ランク

    @Column(name = "dvlp_rnk", nullable = true, length = 20)
    private String dvlpRnk; // 開発ランク名称

    @Column(name = "dvlp_kbn_cd", nullable = true, length = 1)
    private String dvlpKbnCd; // 開発区分

    @Column(name = "dvlp_kbn", nullable = true, length = 20)
    private String dvlpKbn; // 開発区分名称

    @Column(name = "last_update_date", nullable = true, length = 8)
    private String lastUpdateDate; // 最終取込日

    @Column(name = "specs_relation_date", nullable = true)
    private LocalDateTime specsRelationDate; // SPECS連携日
}

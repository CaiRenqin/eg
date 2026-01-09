package jp.co.yamaha_motor.eg.domain.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "if_model", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class IfModelEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "pln_cd", nullable = false, length = 20)
    private String plnCd;// 計画CD

    @Column(name = "oy_plan_cd", nullable = true, length = 10)
    private String oyPlanCd; // 開発代表計画CD

    @Column(name = "pjno", nullable = true, length = 20)
    private String pjno; // PJNo

    @Column(name = "kki_sts", nullable = true, length = 1)
    private String kkiSts; // 公開ステータス

    @Column(name = "snkki_sts", nullable = true, length = 1)
    private String snkkiSts; // 新公開ステータス

    @Column(name = "dvlp_rnk_cd", nullable = true, length = 1)
    private String dvlpRnkCd; // 開発ランク

    @Column(name = "dvlp_rnk", nullable = true, length = 20)
    private String dvlpRnk; // 開発ランク名称

    @Column(name = "ins_ktsk", nullable = true, length = 4)
    private String insKtsk; // 登録型式_G

    @Column(name = "mdl_nm", nullable = true, length = 12)
    private String mdlNm; // モデル名

    @Column(name = "mdl_y", nullable = true, length = 4)
    private String mdlY; // モデルイヤー

    @Column(name = "syhn_kbn_cd", nullable = true, length = 2)
    private String syhnKbnCd; // 商品区分

    @Column(name = "syhn_kbn", nullable = true, length = 30)
    private String syhnKbn; // 商品区分名称

    @Column(name = "dvlp_kbn_cd", nullable = true, length = 1)
    private String dvlpKbnCd; // 開発区分

    @Column(name = "dvlp_kbn", nullable = true, length = 20)
    private String dvlpKbn; // 開発区分名称

    @Column(name = "eg_ktsk", nullable = true, length = 14)
    private String egKtsk; // エンジン形式名

    @Column(name = "tnt_bmn_cd", nullable = true, length = 1)
    private String tntBmnCd; // 担当部門CD

    @Column(name = "tnt_bmn_nm", nullable = true, length = 20)
    private String tntBmnNm; // 担当部門名称

    @Column(name = "ymc_syhn_bnr1_cd", nullable = true, length = 2)
    private String ymcSyhnBnr1Cd; // YMC商品分類１CD

    @Column(name = "ymc_syhn_bnr1", nullable = true, length = 30)
    private String ymcSyhnBnr1; // YMC商品分類１

    @Column(name = "ymc_syhn_bnr2_cd", nullable = true, length = 2)
    private String ymcSyhnBnr2Cd; // YMC商品分類２CD

    @Column(name = "ymc_syhn_bnr2", nullable = true, length = 30)
    private String ymcSyhnBnr2; // YMC商品分類２

    @Column(name = "kns_car_ymd", nullable = true, length = 8)
    private String knsCarYmd; // 完成車立上年月日

    @Column(name = "ymc_ymd", nullable = true, length = 8)
    private String ymcYmd; // YMC立上年月日

    @Column(name = "dvlp_gy", nullable = true, length = 100)
    private String dvlpGy; // 開発概要

    @Column(name = "cbu_ckd_kbn_cd", nullable = true, length = 1)
    private String cbuCkdKbnCd; // CBUCKD区分

    @Column(name = "cbu_ckd_kbn", nullable = true, length = 20)
    private String cbuCkdKbn; // CBUCKD区分名称

    @Column(name = "kns_car_prdc_kj_cd", nullable = true, length = 4)
    private String knsCarPrdcKjCd; // 完成車生産工場

    @Column(name = "kns_car_prdc_kj", nullable = true, length = 40)
    private String knsCarPrdcKj; // 完成車生産工場名

    @Column(name = "chiki_smk_ch", nullable = true, length = 4)
    private String chikiSmkCh; // 地域(仕向地)

    @Column(name = "smk_ch", nullable = true, length = 1700)
    private String smkCh; // 仕向地

    @Column(name = "pl", nullable = true, length = 10)
    private String pl; // 現担当PL

    @Column(name = "syoki_tnt", nullable = true, length = 10)
    private String syokiTnt; // 商品企画担当者

    @Column(name = "ksn_in", nullable = true, length = 1)
    private String ksnIn; // 更新印

    @Column(name = "sykiy_pln_cd", nullable = true, length = 10)
    private String sykiyPlnCd; // 商計用計画CD

    @Column(name = "kki_sts_tky_ymd", nullable = true, length = 8)
    private String kkiStsTkyYmd; // 公開ステータス適用年月日
}

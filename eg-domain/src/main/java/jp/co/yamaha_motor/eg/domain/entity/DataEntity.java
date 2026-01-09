package jp.co.yamaha_motor.eg.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "data", schema = "egdb")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class DataEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "dataId", column = @Column(name = "data_id", nullable = false, length = 20))
    @AttributeOverride(name = "tableId", column = @Column(name = "table_id", nullable = false, length = 10))
    @AttributeOverride(name = "compColumnId", column = @Column(name = "column_id", nullable = false, length = 5))
    private DataEntityId dataEntityId;

    @Column(name = "data", nullable = true, length = 500)
    private String data;

    @Column(name = "ref_data_id", nullable = true, length = 20)
    private String refDataId;

    @Column(name = "ref_column_id", nullable = true, length = 5)
    private String refColumnId;

    @Column(name = "batch_update_date", nullable = true)
    private LocalDateTime batchUpdateDate;
}

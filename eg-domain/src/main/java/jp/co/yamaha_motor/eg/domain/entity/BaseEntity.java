package jp.co.yamaha_motor.eg.domain.entity;

import com.ymsl.solid.jpa.audit.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@Data
public class BaseEntity implements Auditable {

    @CreatedBy
    @Column(name = "create_user")
    private String createdBy; // 作成者

    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime dateCreated; // 作成日

    @LastModifiedBy
    @Column(name = "update_user")
    private String lastUpdatedBy; // 更新者

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime lastUpdated; // 更新日

    @Version
    @Column(name = "update_counter", nullable = false, precision = 38, scale = 0)
    private Integer updateCounter; // 更新カウンタ
}

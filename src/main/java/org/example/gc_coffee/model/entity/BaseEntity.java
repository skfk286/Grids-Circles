package org.example.gc_coffee.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@MappedSuperclass // 정의된 필드들을 상속받은 엔티티 클래스의 테이블 포함시키도록 설정
public class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false) // 업데이트 될 떄 수정되지 않도록.
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

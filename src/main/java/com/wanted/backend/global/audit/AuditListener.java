package com.wanted.backend.global.audit;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuditListener {
    @PrePersist
    public void setCreatedAt(Auditable auditable) {
        BaseTime baseTime = Optional.ofNullable(auditable.getBaseTime()).orElseGet(BaseTime::new);
        baseTime.setCreatedAt(LocalDateTime.now());
        auditable.setBaseTime(baseTime);
    }

    @PreUpdate
    public void setUpdatedAt(Auditable auditable) {
        BaseTime baseTime = auditable.getBaseTime();
        baseTime.setUpdatedAt(LocalDateTime.now());
    }
}


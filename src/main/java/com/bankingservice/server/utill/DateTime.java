package com.bankingservice.server.utill;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// https://jforj.tistory.com/257
// 추가, 수정 시 시간 자동 처리


@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public class DateTime {

    private LocalDateTime LST_MDF_DATETIME;

    @PrePersist
    public void prePersist() {
        this.LST_MDF_DATETIME = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.LST_MDF_DATETIME = LocalDateTime.now();
    }
}

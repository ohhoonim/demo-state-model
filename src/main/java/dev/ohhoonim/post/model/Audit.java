package dev.ohhoonim.post.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record Audit(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt,
        String modifedBy) {
    public Audit modifiedAutit(String operator) {
        String modifiedBy = Objects.requireNonNull(operator, "허용되지 않은 경로로 수정을 시도하고 있습니다.");
        return new Audit(createdAt(), createdBy(), LocalDateTime.now(), modifiedBy);
    }
}
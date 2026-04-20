package dev.ohhoonim.anemic.controller;

import java.time.LocalDateTime;

public record ReplyDto(Long replyId, String contents, LocalDateTime createdAt, String createdBy) {

}

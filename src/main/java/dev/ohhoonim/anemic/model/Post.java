package dev.ohhoonim.anemic.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import dev.ohhoonim.annotations.Entity;

 /*
 - ### 2. 빈약한 빈 (Anemic Domain Model)

 - 객체는 단순히 데이터를 담아 전달하는 컨테이너(DTO 형태) 역할만 수행하며, 
 - 실제 비즈니스 로직은 Service나 Manager 등의 계층에서 절차적으로 처리합니다.

 - - 특징:
 -     - 객체 내부에는 필드와 getter/setter만 존재하는 경우가 많습니다.
 -     - 행위가 데이터와 분리되어 있어 절차지향 프로그래밍과 유사한 형태를 띱니다.
 -     - 전통적인 Spring/JPA 환경에서 매우 흔하게 사용되는 방식입니다.
 - - 장점: 구조가 단순하여 초기 개발 속도가 빠르고 이해하기 쉽습니다.
 - - 단점: 비즈니스 로직이 Service 계층에 집중되면서 Service가 비대해지고, 
 -         데이터 객체의 캡슐화가 깨져 데이터 무결성을 보장하기 어렵습니다.
 - */


@Entity
public class Post {
    private Long postId;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private String createdBy;
    private String modifiedAt;
    private LocalDateTime modifedBy;

    private List<Reply> replies;

    public Post() {}

    public Post(Long postId, String title, String contents) {
        this(postId, title, contents, null, null, null, null);
    }

    public Post(Long postId, String title, String contents, LocalDateTime createdAt,
            String createdBy, String modifiedAt, LocalDateTime modifedBy) {
        this.postId = postId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifedBy = modifedBy;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public LocalDateTime getModifedBy() {
        return modifedBy;
    }

    public void setModifedBy(LocalDateTime modifedBy) {
        this.modifedBy = modifedBy;
    }

    public List<Reply> getReplies() {
        return Collections.unmodifiableList(this.replies);
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }


}

package dev.ohhoonim.post.infra.adapter;

import java.time.LocalDateTime;
import dev.ohhoonim.components.annotation.Entity;
import dev.ohhoonim.components.model.ToModel;
import dev.ohhoonim.post.model.Audit;
import dev.ohhoonim.post.model.EntityId;
import dev.ohhoonim.post.model.Post;

@Entity
public class PostEntity implements ToModel<Post> {

    private Long postId;
    private String title;
    private String contents;
    private LocalDateTime createdAt; 
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifedBy;

    public PostEntity() {}

    public PostEntity(Long postId, String title, String contents, LocalDateTime createdAt,
            String createdBy, LocalDateTime modifiedAt, String modifedBy) {
        this.postId = postId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifedBy = modifedBy;
    }

    @Override
    public Post toModel(Post post) {
        if (post == null) {
            post = new Post(new EntityId(String.valueOf(this.postId)), this.createdBy);
        }
        var audit = new Audit(createdAt, createdBy, modifiedAt, modifedBy);
        return Post.reconsitute(post.postId(), title, contents, audit);
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

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifedBy() {
        return modifedBy;
    }

    public void setModifedBy(String modifedBy) {
        this.modifedBy = modifedBy;
    }

    

}

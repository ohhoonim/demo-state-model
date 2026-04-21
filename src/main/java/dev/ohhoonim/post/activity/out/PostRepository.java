package dev.ohhoonim.post.activity.out;

import dev.ohhoonim.components.annotation.JpaRepository;
import dev.ohhoonim.post.infra.adapter.PostEntity;
import dev.ohhoonim.post.model.EntityId;
import dev.ohhoonim.post.model.Post;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    
}

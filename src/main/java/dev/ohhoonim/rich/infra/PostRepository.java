package dev.ohhoonim.rich.infra;

import dev.ohhoonim.annotations.JpaRepository;
import dev.ohhoonim.rich.model.EntityId;
import dev.ohhoonim.rich.model.Post;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    
}

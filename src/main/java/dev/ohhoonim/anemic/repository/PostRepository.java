package dev.ohhoonim.anemic.repository;

import java.util.List;
import dev.ohhoonim.anemic.model.Post;
import dev.ohhoonim.anemic.model.Reply;
import dev.ohhoonim.annotations.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{

    List<Reply> repliesByPostId(Long postId);
}

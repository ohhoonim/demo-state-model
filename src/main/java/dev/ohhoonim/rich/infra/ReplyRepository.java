package dev.ohhoonim.rich.infra;

import java.util.List;
import dev.ohhoonim.annotations.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long>{
    
    List<ReplyEntity> repliesByPostId(Long postId);
}

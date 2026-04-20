package dev.ohhoonim.anemic.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import dev.ohhoonim.anemic.model.Post;
import dev.ohhoonim.anemic.model.Reply;

@Repository
public class PostDao implements PostRepository {

    private final static List<Post> posts = List.of(new Post(1l, "matthew", "math"), new Post(2l, "ohhoonim", "language"),
                new Post(3l, "alison", "science")) ;

    private final static List<Reply> replies = List.of(
        new Reply(1l, 1l, "지식 공유 감사합니다.", LocalDateTime.now(), "whoAmI"),
        new Reply(2l, 1l, "이건 뭐니?", LocalDateTime.now(), "don"),
        new Reply(3l, 2l, "와우", LocalDateTime.now(), "masic"),
        new Reply(4l, 2l, "부적절한 내용으로 신고되었습니다.", LocalDateTime.now(), "police"),
        new Reply(5l, 3l, "미친, 대반전", LocalDateTime.now(), "whoAmI")
    );

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return posts.stream().filter(p -> p.getPostId().equals(id)).findFirst();
    }

    @Override
    public List<Reply> repliesByPostId(Long postId) {
        return replies.stream().filter(r -> r.getPostId().equals(postId)).toList();
    }
}

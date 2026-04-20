package dev.ohhoonim.rich.application;

import java.util.List;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import dev.ohhoonim.annotations.Transactional;
import dev.ohhoonim.rich.infra.PostRepository;
import dev.ohhoonim.rich.infra.ReplyRepository;
import dev.ohhoonim.rich.model.EntityId;
import dev.ohhoonim.rich.model.Post;
import dev.ohhoonim.rich.model.Reply;

@Service("richPostService")
public class PostService {

    // 예제이므로 adapter를 고려하지 않았다. 
    @Qualifier("richPostDao") private final PostRepository postRepository;
    @Qualifier("richReplyDao") private final ReplyRepository replyRepository;

    public PostService(PostRepository postRepository, ReplyRepository replyRepository) {
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
    }

    @Transactional(readOnly = true)
    public List<PostDto> posts() {
        return postRepository.findAll().stream().map(entity -> entity.toModel(null)).map(postToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostDto post(String postId) {
        Post reqPost = new Post(new EntityId(postId), "system"); // 여기서는 operator를 임의로 지정
        Post post = postRepository.findById(Long.valueOf(reqPost.postId().value()))
                .map(entity -> entity.toModel(reqPost))
                .orElseThrow(() -> new RuntimeException("Post가 존재하지 않습니다."));

        var replies = toReply(reqPost, 2L);
        
        post.addReplies(replies);

        return new PostDto(postId, post.title(), post.contents(), 
                post.audit().createdAt(), post.audit().createdBy(), post.getReplies());
    }

    @Transactional(readOnly = true)
    public List<ReplyDto> replies(String postId) {
        Post reqPost = new Post(new EntityId(postId), "system");

        return toReply(reqPost, 100L).stream().map(r -> new ReplyDto(
            r.replyId(),
            r.contents(),
            r.createdAt(),
            r.createdBy()
        )).toList();

    }

    private List<Reply> toReply(Post reqPost, Long maxLimit) {
        return replyRepository.repliesByPostId(Long.valueOf(reqPost.postId().value()))
                .stream().map(entity -> new Reply(entity.getReplyId(), 
                    entity.getPostId(), 
                    entity.getContents(), 
                    entity.getCreatedAt(), 
                    entity.getCreatedBy())).limit(maxLimit).toList();
    }

    private final Function<Post, PostDto> postToDto =
            post -> new PostDto(String.valueOf(post.postId()), post.title(), post.contents(),
                    post.audit().createdAt(), post.audit().createdBy());

}

package dev.ohhoonim.anemic.service;

import java.util.List;
import org.springframework.stereotype.Service;
import dev.ohhoonim.anemic.controller.PostDto;
import dev.ohhoonim.anemic.controller.ReplyDto;
import dev.ohhoonim.anemic.model.Post;
import dev.ohhoonim.anemic.model.Reply;
import dev.ohhoonim.anemic.repository.PostRepository;

@Service("anemicPostService")
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> list() {
        var posts = postRepository.findAll();
        // Post --> PostDto
        return posts.stream().map(p -> new PostDto(p.getPostId(), p.getTitle(), p.getContents()))
                .toList();
    }

    public PostDto post(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("찻으려고하는 Post가 없습니다."));
        return new PostDto(post.getPostId(), post.getTitle(), post.getContents());
    }

    public List<ReplyDto> replies(Long postId) {
        List<Reply> replies = postRepository.repliesByPostId(postId);
        return replies.stream().map(r -> new ReplyDto(r.getReplyId(), r.getContents(),
                r.getCreatedAt(), r.getCreatedBy())).toList();
    }

}

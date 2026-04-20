package dev.ohhoonim.anemic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import dev.ohhoonim.anemic.service.PostService;

@RestController
@RequestMapping("/anemic")
public class PostController {

    @Qualifier("anemicPostService")
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    @GetMapping("/posts")
    public List<PostDto> posts() {
        return postService.list();
    }

    @GetMapping("/posts/{postId}")
    public PostDto post(@PathVariable("postId") Long postId) {
        return postService.post(postId);
    }

    @GetMapping("/posts/{postId}/replies")
    public List<ReplyDto> replies(@PathVariable("postId") Long postId) {
        return postService.replies(postId);
    }
}

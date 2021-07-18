package dnt.jpaentitygraph.controller;

import dnt.jpaentitygraph.model.Post;
import dnt.jpaentitygraph.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPosts(@PathVariable("postId") Long postId) {
        Post post = postService.getPostById(postId);
//        postService.doSomething(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}

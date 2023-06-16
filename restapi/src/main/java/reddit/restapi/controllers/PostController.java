package reddit.restapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reddit.restapi.models.Post;
import reddit.restapi.models.Subreddit;
import reddit.restapi.repositories.PostRepo;
import reddit.restapi.services.PostService;

import java.util.List;
//@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    private PostRepo postRepo;

    public PostController(PostService postService, PostRepo postRepo) {
        this.postService = postService;
        this.postRepo = postRepo;
    }
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) throws Exception {

        Post post = postRepo.getPostById(id);

        return post;
    }

    @GetMapping("/home")
    public List<Post> homepagePostsByDate() throws Exception {

        return postService.homepagePostsByDate();
    }

    @GetMapping("subreddits/{subredditId}")
    public List<Post> getSubredditPosts(@PathVariable Long subredditId) throws Exception {
        return postService.getAllSubredditPosts(subredditId);
    }

    @GetMapping("user/{userId}")
    public List<Post> getAllPostsbyUserId(@PathVariable Long userId) throws Exception {
        return postService.getAllPostsbyUserId(userId);
    }


    @PostMapping("")
    public Post createPost(@RequestBody Post newPost, Authentication authentication) throws Exception {


        return postService.createPost(newPost, authentication);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, Authentication authentication, @RequestBody Post post) throws Exception {

        return postService.updatePost(post, id, authentication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id, Authentication authentication) throws Exception {
        postService.deletePostById(id, authentication);
        return ResponseEntity.ok("Post deleted successfully");

    }

}

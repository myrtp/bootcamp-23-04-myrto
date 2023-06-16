package reddit.restapi.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reddit.restapi.models.Comment;
import reddit.restapi.repositories.CommentRepo;
import reddit.restapi.services.CommentService;
import java.util.List;


//@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {



        private CommentService commentService;
        private CommentRepo commentRepo;

        public CommentController(CommentService commentService, CommentRepo commentRepo) {
            this.commentService = commentService;
            this.commentRepo = commentRepo;
        }

        @GetMapping("/{id}")
        public Comment getCommentById(@PathVariable Long id) throws Exception {
            Comment comment = commentService.getCommentById(id);
            return comment;
        }

        @GetMapping("{postId}/comments")
        public List<Comment> getCommentsByPostId(@PathVariable Long postId) throws Exception {
            return commentService.getCommentsByPostId(postId);
        }

        @PostMapping("")
        public Comment createComment(@RequestBody Comment newComment, Authentication authentication) throws Exception {
            return commentService.createComment(newComment, authentication);
        }

        @PutMapping("/{id}")
        public Comment updateComment(@PathVariable Long id, Authentication authentication, @RequestBody Comment comment) throws Exception {
            return commentService.updateComment(comment, id, authentication);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCommentById(@PathVariable Long id, Authentication authentication) throws Exception {
            commentService.deleteCommentById(id, authentication);
            return ResponseEntity.ok("Comment deleted successfully");
        }
    }


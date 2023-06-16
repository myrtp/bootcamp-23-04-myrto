package reddit.restapi.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.Comment;
import reddit.restapi.models.Post;
import reddit.restapi.repositories.CommentRepo;
import reddit.restapi.repositories.PostRepo;
import reddit.restapi.util.AuthorizationChecks;
import java.time.Instant;
import java.util.List;

@Service
public class CommentService {

    private CommentRepo commentRepo;

    private AuthorizationChecks authChecks;

    public CommentService(CommentRepo commentRepo, AuthorizationChecks authChecks) {
        this.commentRepo = commentRepo;
        this.authChecks = authChecks;
    }

    public List<Comment> getCommentsByPostId(Long  postId) throws Exception{
        return commentRepo.findCommentsByPostId(postId);
    }

    public Comment getCommentById(Long id) throws Exception {
        return commentRepo.findById(id).orElseThrow(() -> new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_USER_NOT_FOUND",
                "Post does not exist"));
    }

    public Comment createComment(Comment newComment, Authentication authentication) throws Exception {
        if (newComment.getId() != null) {
            throw new RestAppException(HttpStatus.FORBIDDEN, "ERROR_CODE_FORBIDDEN", "COMMENT ID EXISTS!");
        }

        Long subredditId = newComment.getSubredditId();
        Long userId = newComment.getUserId();
        Long postId = newComment.getPostId();
        authChecks.isMember(authentication, subredditId);
        authChecks.isTheSameUser(userId, authentication);
        authChecks.isMember(authentication, postId);
        Instant newCreatedAt = Instant.now();
        newComment.setCreatedAt(newCreatedAt);

        return commentRepo.save(newComment);
    }


    public Comment updateComment(Comment requestComment, Long id, Authentication authentication) throws Exception {
        Long userId = authChecks.GetUserIDbyJWT(authentication);
        authChecks.isTheSameUser(userId, authentication);

        Comment commentFromDB = getCommentById(id);
        commentFromDB.setText(requestComment.getText());
        commentFromDB.setCommReply(requestComment.getCommReply());
        Instant newUpdatedAt = Instant.now();
        commentFromDB.setUpdatedAt(newUpdatedAt);

        return commentRepo.save(commentFromDB);
    }

    @Transactional
    public void deleteCommentById(Long id, Authentication authentication) throws Exception {
        Comment comment = getCommentById(id);
        Long userId = authChecks.GetUserIDbyJWT(authentication);
        authChecks.isTheSameUser(userId, authentication);

        commentRepo.deleteById(id);
    }


}

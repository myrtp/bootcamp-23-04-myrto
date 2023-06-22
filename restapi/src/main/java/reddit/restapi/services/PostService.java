package reddit.restapi.services;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.Post;
import reddit.restapi.models.Subreddit;
import reddit.restapi.models.User;
import reddit.restapi.models.UserSubred;
import reddit.restapi.repositories.PostRepo;
import reddit.restapi.util.AuthorizationChecks;

import java.time.Instant;
import java.util.List;
import java.util.Objects;


@Service
public class PostService {
    private PostRepo postRepo;

    private AuthorizationChecks authChecks;

    public PostService(PostRepo postRepo,  AuthorizationChecks authChecks) {
        this.postRepo = postRepo;
        this.authChecks = authChecks;
    }

    public Post getPostById(Long id) throws Exception {
        return postRepo.findById(id).orElseThrow(() -> new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_USER_NOT_FOUND",
                "Post does not exist"));
    }

    public List<Post> getAllSubredditPosts( Long  subredditId) throws Exception{
        return postRepo.findPostsBySubredditId(subredditId);
    }
    public List<Post> getAllPostsbyUserId( Long  userId) throws Exception{
        return postRepo.findPostsByUserId(userId);
    }
    public List<Post> homepagePostsByDate() throws Exception{
        return postRepo.sortPostsByCreationDate();
    }

    public Post createPost(Post newPost, Authentication authentication) throws Exception{

        if (newPost.getId() != null) {
            throw new RestAppException(HttpStatus.FORBIDDEN, "ERROR_CODE_FORBIDDEN", "SUBREDDIT ID EXISTS!");


        }
        Long subredditId = newPost.getSubredditId();

        if (!authChecks.isMember(authentication, subredditId)) {
            throw new RestAppException(HttpStatus.UNAUTHORIZED, "ERROR_CODE_UNAUTHORIZED",
                    "User does not have authority to make changes");
        }
        Instant newCreatedAt = Instant.now();
        newPost.setCreatedAt(newCreatedAt);

        return    postRepo.save(newPost);
    }




    public Post updatePost(Post requestPost, Long id, Authentication authentication) throws Exception {
        Long userId = authChecks.GetUserIDbyJWT(authentication);

        if (!authChecks.isTheSameUser(userId,authentication)) {
            throw new RestAppException(HttpStatus.UNAUTHORIZED, "ERROR_CODE_UNAUTHORIZED",
                    "User does not have authority to make changes");
        }
        Post PostfromDB = this.getPostById(id);
        PostfromDB.setTitle(requestPost.getTitle());
        PostfromDB.setText(requestPost.getText());
        Instant newUpdatedat = Instant.now();
        requestPost.setUpdatedAt(newUpdatedat);
        return postRepo.save(PostfromDB);

    }


    @Transactional
    public void deletePostById(Long id, Authentication authentication) throws Exception {
        Long userId = authChecks.GetUserIDbyJWT(authentication);

        if (!authChecks.isTheSameUser(userId,authentication)) {
            throw new RestAppException(HttpStatus.UNAUTHORIZED, "ERROR_CODE_UNAUTHORIZED",
                    "User does not have authority to make changes");
        }
        postRepo.deleteById(id);
    }

    }



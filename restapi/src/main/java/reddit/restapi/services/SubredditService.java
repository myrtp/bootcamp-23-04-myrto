package reddit.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.Subreddit;
import reddit.restapi.models.User;
import reddit.restapi.models.UserSubred;
import reddit.restapi.repositories.UserSubredRepo;
import reddit.restapi.repositories.SubredditRepo;
import reddit.restapi.util.AuthorizationChecks;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class SubredditService {
    private SubredditRepo subredditRepo;
    private UserSubredRepo userSubredditRepo;
    private UsersService userService;

    private AuthorizationChecks authChecks;


    @Autowired
    public SubredditService(SubredditRepo subredditRepo, UserSubredRepo userSubredditRepo, UsersService userService, AuthorizationChecks authChecks) {
        this.subredditRepo = subredditRepo;
        this.userSubredditRepo = userSubredditRepo;
        this.userService = userService;
        this.authChecks = authChecks;

    }

    public List<Subreddit> getAllSubreddits() {
        return subredditRepo.findAll();
    }

    public Subreddit getSubredditById(Long id) throws Exception {
        return subredditRepo.findById(id).orElseThrow(() -> new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_USER_NOT_FOUND",
                "Subreddit does not exist"));
    }

    public Subreddit getAllSubredditsbyCriteria(String title) throws Exception {
        return subredditRepo.findAnyByTitle(title).orElseThrow(() -> new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_USER_NOT_FOUND",
                "Subreddit not found"));
    }


    public Subreddit updateSubreddit(Subreddit requestSubreddit, Long id, Authentication authentication) throws Exception {

        authChecks.isAdmin(authentication, id);
        Subreddit subredditfromDB = this.getSubredditById(id);
        subredditfromDB.setTitle(requestSubreddit.getTitle());
        subredditfromDB.setDescription(requestSubreddit.getDescription());

        return subredditRepo.save(subredditfromDB);

    }


    public Subreddit createSubreddit(Subreddit newSubreddit, Authentication authentication) throws Exception{
        if (newSubreddit.getId() != null) {
            throw new RestAppException(HttpStatus.FORBIDDEN, "ERROR_CODE_FORBIDDEN", "SUBREDDIT ID EXISTS!");


        }

//        Optional<Subreddit> storedSubredditTitle = subredditRepo.findAnyByTitle(newSubreddit.getTitle());
//
//        if (storedSubredditTitle != null) {
//            throw new RestAppException(HttpStatus.FORBIDDEN, "ERROR_CODE_FORBIDDEN", "EMAIL EXISTS!");
//        }

        Instant newCreatedAt = Instant.now();
        newSubreddit.setCreatedAt(newCreatedAt);
        subredditRepo.save(newSubreddit);
        UserSubred userSubred = new UserSubred();
        userSubred.setSubredditId(newSubreddit.getId());
        Long userId = authChecks.GetUserIDbyJWT(authentication);
        User user = userService.getUserById(userId); // Fetch the user from the repository using the userId
        userSubred.setUser(user);
        // Assuming you have the user object available
        userSubred.setRole("admin");
        userSubredditRepo.save(userSubred);


        return newSubreddit;
    }


    public void joinSubreddit(Authentication authentication, Long id) throws Exception {
        // Check if the user is already a member
        authChecks.isMember(authentication, id);
        Long userId = authChecks.GetUserIDbyJWT(authentication);
        User user = userService.getUserById(userId);
        UserSubred userSubred = new UserSubred();
        userSubred.setUser(user);
        userSubred.setSubredditId(id);
        userSubred.setRole("member");
        userSubredditRepo.save(userSubred);
    }


    @Transactional
    public void deleteSubredditById(Long id, Authentication authentication) throws Exception {
        authChecks.isAdmin(authentication, id);
        subredditRepo.deleteById(id);
    }





}


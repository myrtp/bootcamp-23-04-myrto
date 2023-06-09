package reddit.restapi.services;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.Subreddit;
import reddit.restapi.models.User;
import reddit.restapi.models.UserSubred;
import reddit.restapi.repositories.UserSubredRepo;
import reddit.restapi.repositories.SubredditRepo;
import java.util.List;
import java.util.Optional;

@Service
public class SubredditService {
    private SubredditRepo subredditRepo;
    private UserSubredRepo userSubredditRepo;

    @Autowired
    public SubredditService(SubredditRepo subredditRepo, UserSubredRepo userSubredditRepo) {
        this.subredditRepo = subredditRepo;
        this.userSubredditRepo = userSubredditRepo;
    }

    public List<Subreddit> getAllSubs() {
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
    public Subreddit createSubreddit(Subreddit newSubreddit) throws Exception {


        if (newSubreddit.getId() != null) {
            throw new Exception("New user should not have id. I WILL DECIDE IT!!!!");
        }
        
        Optional<Subreddit> storedTitle = subredditRepo.findAnyByTitle(newSubreddit.getTitle());

        if (storedTitle != null) {
            throw new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_FORBIDDEN", "EMAIL EXISTS!");
        }


        return subredditRepo.save(newSubreddit);

    }


    public Subreddit updateSubreddit(Subreddit requestSubreddit, Long id, Authentication authentication) throws Exception {

        isAdmin(authentication, id);
        Subreddit subredditfromDB = this.getSubredditById(id);
        subredditfromDB.setTitle(requestSubreddit.getTitle());
        subredditfromDB.setDescription(requestSubreddit.getDescription());

        return subredditRepo.save(subredditfromDB);

    }

    public boolean isAdmin(Authentication authentication, Long subredditId) throws Exception {
        List<UserSubred> userSubreds = userSubredditRepo.findUserSubredBySubredditId(subredditId);
        Jwt userJwt = (Jwt) authentication.getPrincipal();
        Long userId = (Long) userJwt.getClaims().get("userId");

        boolean hasRightToEditSubreddit = false;

        for (UserSubred userSubred : userSubreds) {
            if (userSubred.getUser().getId().equals(userId) && userSubred.getRole().equals("ADMIN")) {
                hasRightToEditSubreddit = true;
            }

        }
        if (hasRightToEditSubreddit = false) {
            throw new RestAppException(HttpStatus.UNAUTHORIZED, "ERROR_CODE_UNAUTHORIZED",
                    "User does not have authority to make changes");
        }
        return true;
    }
    @Transactional
    public void deleteSubredditById(Long id, Authentication authentication) throws Exception {
        isAdmin(authentication, id);
        subredditRepo.deleteById(id);
    }





}


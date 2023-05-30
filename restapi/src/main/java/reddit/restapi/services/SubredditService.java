package reddit.restapi.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.Subreddit;
import reddit.restapi.models.UserSubred;
import reddit.restapi.repositories.UserSubredRepo;
import reddit.restapi.repositories.SubredditRepo;

import java.util.List;
@Service
public class SubredditService {
    private SubredditRepo subredditRepo;
    private UserSubredRepo userSubredditRepo;

   @Autowired
    public SubredditService(SubredditRepo subredditRepo){
       this.subredditRepo = subredditRepo;
   }
    public SubredditService(UserSubredRepo userSubredditRepo) {
        this.userSubredditRepo = userSubredditRepo;
    }
    public List<Subreddit> getAllSubs() {
        return  subredditRepo.findAll();
    }

    public Subreddit getSubredditById(Long id) throws Exception {
        return subredditRepo.findById(id).orElseThrow(() ->new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_USER_NOT_FOUND",
                "Subreddit does not exist"));
    }

    public Subreddit getAllSubredditsbyCriteria(String title) throws Exception {
        return subredditRepo.findAnyByTitle(title).orElseThrow(() -> new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_USER_NOT_FOUND",
                "Subreddit not found"));
    }

//    public boolean isSubredditAdmin(Long id, Long id){
//        UserSubred userSubred = UserSubredRepo.findByUserIdAndSubredditId(userID, subredditID);
//    }
}


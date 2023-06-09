package reddit.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.UserSubred;
import reddit.restapi.repositories.UserSubredRepo;

import java.util.List;

@Service
public class UserSubredditService {
    private UserSubredRepo userSubredRepo;
    @Autowired
    public UserSubredditService(UserSubredRepo userSubredRepo){
        this.userSubredRepo = userSubredRepo;
    }

    public List<UserSubred> getUserbySubredditId(Long subredditId) throws Exception {
        return userSubredRepo.findUserSubredBySubredditId(subredditId);
    }


}

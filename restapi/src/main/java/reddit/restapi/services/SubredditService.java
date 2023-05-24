package reddit.restapi.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.restapi.models.Subreddit;
import reddit.restapi.models.User;
import reddit.restapi.repositories.SubredditRepo;

import java.util.List;
@Service
public class SubredditService {
    private SubredditRepo subredditRepo;

   @Autowired
    public SubredditService(SubredditRepo subredditRepo){
       this.subredditRepo = subredditRepo;
   }

    public List<Subreddit> getAllSubs() {
        return  subredditRepo.findAll();
    }

    public Subreddit getSubredditById(Long id) throws Exception {
        return subredditRepo.findById(id).orElseThrow(() -> new RuntimeException("Sub not found"));
    }



}

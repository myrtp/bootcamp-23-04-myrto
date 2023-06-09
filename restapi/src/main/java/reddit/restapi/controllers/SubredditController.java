package reddit.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.Subreddit;
import reddit.restapi.models.User;
import reddit.restapi.models.UserSubred;
import reddit.restapi.repositories.SubredditRepo;
import reddit.restapi.repositories.UserSubredRepo;
import reddit.restapi.services.SubredditService;
import reddit.restapi.services.UserSubredditService;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/subreddits")
public class SubredditController {

    private SubredditService subredditService;
    private SubredditRepo subredditRepository;

    private UserSubredditService userSubredditService;
    private UserSubredRepo userSubredRepo;

    public SubredditController(SubredditService subredditService, SubredditRepo subredditRepository, UserSubredditService userSubredditService, UserSubredRepo userSubredRepo) {
        this.subredditService = subredditService;
        this.subredditRepository = subredditRepository;
        this.userSubredditService = userSubredditService;
        this.userSubredRepo = userSubredRepo;
    }


    @GetMapping("/{id}")
    public Subreddit getSubredditById(@PathVariable Long id) throws Exception {

        //code that gets user by id from database
        Subreddit subreddit = subredditRepository.getSubredditById(id);

        return subreddit;
    }

    @GetMapping("")
    public Optional<Subreddit> getAllSubredditsbyCriteria(@RequestParam(required = false) String title) throws Exception {

        return Optional.ofNullable(subredditService.getAllSubredditsbyCriteria(title));

    }

    @PostMapping("")
    public Subreddit createSubreddit(@RequestBody Subreddit newSubreddit) throws Exception {


        return subredditService.createSubreddit(newSubreddit);
    }

    @PutMapping("/{id}")
    public Subreddit updateSubreddit(@PathVariable Long id,  Authentication authentication, @RequestBody Subreddit subreddit) throws Exception {

     return subredditService.updateSubreddit(subreddit, id, authentication);
    }

    @GetMapping("{subredditId}/members")
    public List<UserSubred> getUserIdbySubredditId(@PathVariable Long subredditId) throws Exception {

        return userSubredditService.getUserbySubredditId(subredditId);
    }
//    @DeleteMapping("/{id}")
//    public Map<String, String> deleteSubredditById(@PathVariable Long id, Authentication authentication) throws Exception{
//        authorizationChecks.isTheSameUser(id, authentication);
//        userService.deleteUserById(id);
//        Map<String, String> resp = new HashMap<>();
//        resp.put("deleteStatus", "Success");
////        ObjectMapper mapper = new ObjectMapper();
////        String message = mapper.writeValueAsString("deleted user");
////        return message;
//        return resp;
//    }
}



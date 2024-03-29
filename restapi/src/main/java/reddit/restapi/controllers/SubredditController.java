package reddit.restapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.Subreddit;
import reddit.restapi.models.UserSubred;
import reddit.restapi.repositories.SubredditRepo;
import reddit.restapi.repositories.UserSubredRepo;
import reddit.restapi.services.SubredditService;
import reddit.restapi.services.UserSubredditService;

import java.util.List;

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

//    @GetMapping("")
//    public List<Subreddit> getAllSubreddits() throws Exception {
//
//        return subredditService.getAllSubreddits();
//
//    }

    @GetMapping("/title")
    public Object getAllSubreddits(@RequestParam(required = false) String title) throws Exception {
        if (title != null) {
            return List.of(subredditService.getAllSubredditsbyCriteria(title)).toString();
        } else {
            return subredditService.getAllSubreddits();
        }
    }
//    @GetMapping("/{id}/title")
//    public String getAllSubredditsbyCriteria(@RequestParam(required = false) String title) throws Exception{
//        return List.of(subredditService.getAllSubredditsbyCriteria(title)).toString();
//    }
    @PostMapping("")
    public Subreddit createSubreddit(@RequestBody Subreddit newSubreddit, Authentication authentication) throws Exception {


        return subredditService.createSubreddit(newSubreddit, authentication);
    }

    @PutMapping("/{id}")
    public Subreddit updateSubreddit(@PathVariable Long id, Authentication authentication, @RequestBody Subreddit subreddit) throws Exception {

        return subredditService.updateSubreddit(subreddit, id, authentication);
    }

    @GetMapping("{id}/members")
    public List<UserSubred> getUserIdbySubredditId(@PathVariable Long id) throws Exception {

        return userSubredditService.getUserbySubredditId(id);
    }
    @GetMapping("{id}/admin")
    public UserSubred getAdminBySubredditId(@PathVariable Long id) throws Exception {


        return userSubredditService.findAdminBySubredditId(id);

    }

    @GetMapping("{id}/member")
    public UserSubred findMemberBySubredditId(@PathVariable Long id) throws Exception {


        return userSubredditService.findMemberBySubredditId(id);

    }

//    @GetMapping("{id}/{userId}/role")
//    public String getUserRoleForSubreddit(@PathVariable Long id, @PathVariable Long userId) throws Exception {
//        return subredditService.getUserRoleForSubreddit(id, userId);
//    }



    @PostMapping("/{id}/join") public ResponseEntity<String> joinSubreddit(@PathVariable Long id,  Authentication authentication) throws Exception {
        subredditService.joinSubreddit( authentication,  id);
        return ResponseEntity.ok("joined subreddit");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id, Authentication authentication) throws Exception {
        subredditService.deleteSubredditById(id, authentication);
        return ResponseEntity.ok("Subreddit deleted successfully");

    }


}




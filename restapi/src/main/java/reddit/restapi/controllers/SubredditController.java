package reddit.restapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reddit.restapi.models.Subreddit;
import reddit.restapi.repositories.SubredditRepo;
import reddit.restapi.services.SubredditService;

import java.util.Optional;

@RestController
@RequestMapping("/subreddits")
    public class SubredditController {

        private SubredditService subredditService;
        private SubredditRepo subredditRepository;

        @Autowired
        public SubredditController(SubredditService subredditService, SubredditRepo subredditRepository) {
            this.subredditService = subredditService;
            this.subredditRepository = subredditRepository;
        }

    @GetMapping("/{id}")
    public Subreddit getSubredditById(@PathVariable Long id) throws Exception {

        //code that gets user by id from database
        Subreddit subreddit = subredditRepository.getSubredditById(id);

        return subreddit;
    }
    @GetMapping("")
    public Optional <Subreddit> getAllSubredditsbyCriteria(@RequestParam(required = false) String title) throws Exception
    {

        return Optional.ofNullable(subredditService.getAllSubredditsbyCriteria(title));

    }

    }



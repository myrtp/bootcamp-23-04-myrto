package reddit.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reddit.restapi.models.Interest;
import reddit.restapi.repositories.InterestRepo;
import reddit.restapi.services.InterestService;
@CrossOrigin
@RestController
@RequestMapping("/interests")
public class InterestController {
    private InterestService interestService;
    private InterestRepo interestRepo;

    @Autowired
    public InterestController(InterestService interestService, InterestRepo interestRepo) {
        this.interestRepo = interestRepo;
        this.interestService = interestService;
    }

    @GetMapping("/{id}")
    public Interest getInterestById(@PathVariable Long id) throws Exception {

        //code that gets user by id from database
        Interest interest = interestRepo.getInterestById(id);

        return interest;
    }
}

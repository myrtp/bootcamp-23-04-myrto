package reddit.restapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reddit.restapi.models.User;
import reddit.restapi.services.UsersService;
import reddit.restapi.repositories.UserRepo;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService userService;
    private UserRepo userRepository;

    @Autowired
    public UsersController(UsersService userService, UserRepo userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


//    @GetMapping("")
//    public List <User> getAllUsersbyCriteria(@RequestParam(required = false) String username) {
//
//        return userService.getAllUsersbyCriteria(username);
//    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {

        //code that gets user by id from database
        return userService.getUserById(id);
    }

    @PostMapping("")
    public User createUser(@RequestBody User newUser) throws Exception {


        return userService.signUpUser(newUser);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {

        //code that gets user by id from database
        return userService.updateUser(user,id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserbyId(@RequestBody User user, @PathVariable Long id) throws Exception {
    }

}

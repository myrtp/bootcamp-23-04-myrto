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


    @GetMapping("")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {

        //code that gets user by id from database
        return userService.getUserById(id);
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) throws Exception {


        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {

        //code that gets user by id from database
        return userRepository.save(user);
    }



}
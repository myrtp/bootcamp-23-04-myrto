package reddit.restapi.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reddit.restapi.models.User;
import reddit.restapi.models.dtos.UserDTO;
import reddit.restapi.services.UsersService;
import reddit.restapi.repositories.UserRepo;
import reddit.restapi.util.AuthorizationChecks;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService userService;
    private UserRepo userRepository;
    private AuthorizationChecks authorizationChecks;

    @Autowired
    public UsersController(UsersService userService, UserRepo userRepository, AuthorizationChecks authorizationChecks) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authorizationChecks = authorizationChecks;
    }

    @GetMapping("")
    public Optional<User> getAllUsersbyCriteria(@RequestParam(required = false) String username) throws Exception {

        return Optional.ofNullable(userService.getAllUserbyUsername(username));
    }

    @GetMapping("/{id}")
//    public UserDTO getUserById(@PathVariable Long id) throws Exception {
        public UserDTO getUserById(@PathVariable Long id, Authentication authentication) throws Exception {
            authorizationChecks.isTheSameUser(id, authentication);
            //code that gets user by id from database
//        return userService.getUserById(id);

            User requestedUser = userService.getUserById(id);
            UserDTO requestedUserDTO = UserDTO.builder()
                    .email(requestedUser.getEmail())
                    .username(requestedUser.getUsername())
                    .password(requestedUser.getPassword())
                    .id(requestedUser.getId())
                    .profileimage(requestedUser.getProfileimage())
                    .build();
            return requestedUserDTO;
    }

    @PostMapping("")
    public User createUser(@RequestBody User newUser) throws Exception {


        return userService.signUpUser(newUser);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {

        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteUserById(@PathVariable Long id, Authentication authentication) throws Exception{
        authorizationChecks.isTheSameUser(id, authentication);
        userService.deleteUserById(id);
        Map<String, String> resp = new HashMap<>();
        resp.put("deleteStatus", "Success");
//        ObjectMapper mapper = new ObjectMapper();
//        String message = mapper.writeValueAsString("deleted user");
//        return message;
        return resp;
    }
}

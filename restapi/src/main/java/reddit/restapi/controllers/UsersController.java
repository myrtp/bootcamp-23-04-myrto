package reddit.restapi.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reddit.restapi.models.Post;
import reddit.restapi.models.User;
import reddit.restapi.models.dtos.UserDTO;
import reddit.restapi.services.UsersService;
import reddit.restapi.repositories.UserRepo;
import reddit.restapi.util.AuthorizationChecks;

import java.util.HashMap;
import java.util.List;
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

//    @GetMapping("")
//    public List<User> getAllUsers() throws Exception {
//
//        return userService.getAllUsers();
//    }


//    @GetMapping("/profile")
//    public User getUserfromAuthentication(Authentication authentication) throws Exception {
//        return userService.getUserfromAuthentication( authentication);
//    }

    @GetMapping("/{id}")
        public UserDTO getUserById(@PathVariable Long id, Authentication authentication) throws Exception {
            authorizationChecks.isTheSameUser(id, authentication);

            User requestedUser = userService.getUserById(id);
            UserDTO requestedUserDTO = UserDTO.builder()
                    .email(requestedUser.getEmail())
                    .username(requestedUser.getUsername())
                    .password(requestedUser.getPassword())
                    .id(requestedUser.getId())
                    .profileimage(requestedUser.getProfileimage())
                    .dob(requestedUser.getDob())
                    .build();
            return requestedUserDTO;
    }

    @PostMapping("")
    public User createUser(@RequestBody User newUser) throws Exception {


        return userService.createUser(newUser);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) throws Exception {

        return userService.updateUser(user, id);
    }

    @GetMapping("/{id}/username")
    public String getUsernameById(@PathVariable Long userId) throws Exception{
        return userService.getUsernameById(userId);
    }


    @DeleteMapping("/{id}")
    public Map<String, String> deleteUserById(@PathVariable Long id, Authentication authentication) throws Exception{
        authorizationChecks.isTheSameUser(id, authentication);
        userService.deleteUserById(id);
        Map<String, String> resp = new HashMap<>();
        resp.put("deleteStatus", "Success");

        return resp;
    }


}

package reddit.restapi.services;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import reddit.restapi.exceptions.RestAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reddit.restapi.models.User;
import reddit.restapi.models.security.UserPrincipalDTO;
import reddit.restapi.repositories.UserRepo;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UsersService implements UserDetailsService {
    private UserRepo userRepository;


    @Autowired
    public UsersService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new RestAppException(HttpStatus.NOT_FOUND,
                "ERROR_CODE_USER_NOT_FOUND", "User not found"));
    }

    public static boolean emailValidation(String email, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public User signUpUser(User newUser) throws Exception {


        if (newUser.getId() != null) {
            throw new Exception("New user should not have id. I WILL DECIDE IT!!!!");
        }

        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isValidEmail = emailValidation(newUser.getEmail(), regexPattern);

        if (isValidEmail != true) {
            throw new Exception("Wrong email!!!!!");
        }

        User storedUserEmail = userRepository.findByEmail(newUser.getEmail());

        if (storedUserEmail != null) {
            throw new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_FORBIDDEN", "EMAIL EXISTS!");
        }

        User storedUserUsername = userRepository.findByusername(newUser.getUsername());

        if (storedUserUsername != null) {
            throw new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_FORBIDDEN", "USERNAME EXISTS!");
        }

        return userRepository.save(newUser);

    }

    //   Update User
    public User updateUser(User requestUser, Long id) throws Exception {

        if (!requestUser.getId().equals(id)) {
            throw new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_FORBIDDEN", "REQUEST FORBIDDEN!");
        }
        User userfromDB = this.getUserById(id);

        userfromDB.setUsername(requestUser.getUsername());
        userfromDB.setEmail(requestUser.getEmail());
        userfromDB.setUsername(requestUser.getUsername());
        userfromDB.setPassword(requestUser.getPassword());
        userfromDB.setProfileimage(requestUser.getProfileimage());
        userfromDB.setEmail(requestUser.getEmail());
        return userRepository.save(requestUser);

    }

    public User getAllUserbyUsername(String username) throws Exception {
        return userRepository.findAnyByUsername(username).orElseThrow(()
                -> new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_USER_NOT_FOUND", "User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userToBeLoggedIn = null;
        try {
            userToBeLoggedIn = this.getAllUserbyUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("USER_NOT_FOUND", e);
        }


        return new UserPrincipalDTO(userToBeLoggedIn);
    }

    @Transactional
    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }

    public User getUserfromAuthentication(Authentication authentication) throws Exception{
        String username =
                (String) ((Jwt)((JwtAuthenticationToken)authentication).getPrincipal()).getClaims().get("username");
        return getAllUserbyUsername(username);
    }

}


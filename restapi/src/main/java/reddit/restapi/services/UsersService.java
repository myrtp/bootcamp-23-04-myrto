package reddit.restapi.services;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import reddit.restapi.exceptions.RestAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reddit.restapi.models.User;
import reddit.restapi.models.dtos.SignUpUserDTO;
import reddit.restapi.models.security.UserPrincipalDTO;
import reddit.restapi.repositories.UserRepo;
import reddit.restapi.util.SignUpDTOTransformer;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UsersService implements UserDetailsService {
    private UserRepo userRepository;

    private SignUpDTOTransformer signUpDTOTransformer;

    @Autowired
    public UsersService(UserRepo userRepository,SignUpDTOTransformer signUpDTOTransformer) {
        this.userRepository = userRepository;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new RestAppException(HttpStatus.NOT_FOUND,
                "ERROR_CODE_USER_NOT_FOUND", "User not found"));
    }

    public String getUsernameById(Long id) throws Exception {
        User user = getUserById(id);
        if (user != null) {
            return user.getUsername();
        }
        throw new RestAppException(HttpStatus.NOT_FOUND,
                "ERROR_CODE_USER_NOT_FOUND", "User not found"); // or throw an exception if desired
    }


    public static boolean emailValidation(String email, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public User createUser(User newUser) throws Exception {


        if (newUser.getId() != null) {
            throw new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_FORBIDDEN", "USER ID EXISTS!");
        }

        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isValidEmail = emailValidation(newUser.getEmail(), regexPattern);

        if (isValidEmail != true) {
            throw new RestAppException(HttpStatus.BAD_REQUEST, "ERROR_CODE_BAD_REQUEST", "INVALID EMAIL!");
        }

        User storedUserEmail = userRepository.findByEmail(newUser.getEmail());

        if (storedUserEmail != null) {
            throw new RestAppException(HttpStatus.FORBIDDEN, "ERROR_CODE_FORBIDDEN", "EMAIL EXISTS!");
        }

        User storedUserUsername = userRepository.findByusername(newUser.getUsername());

        if (storedUserUsername != null) {
            throw new RestAppException(HttpStatus.FORBIDDEN, "ERROR_CODE_FORBIDDEN", "USERNAME EXISTS!");
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

    public User signUpUser(SignUpUserDTO user) throws Exception {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new RestAppException(HttpStatus.BAD_REQUEST, "ERROR_CODE_PASSWORD_MISMATCH", "Passwords do not match");
        }
        User newUser = signUpDTOTransformer.toEntity(user);
        return this.createUser(newUser);

    }
}


package reddit.restapi.services;
import reddit.restapi.exceptions.RestAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reddit.restapi.models.User;
import reddit.restapi.repositories.UserRepo;
import java.util.List;
import java.util.regex.Pattern;
//implements UserDetailsService
@Service
public class UsersService {
    private UserRepo userRepository;


    @Autowired
    public UsersService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

        public List<User> getAllUsers() {
        return  userRepository.findAll();
    }
//    public List<User> getAllUsersbyCriteria(String username) {
//        if (username == null) {
//            return  userRepository.findAll();
//        }
//        return (List<User>) userRepository.findByusername(username);
//    }

    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public static boolean emailValidation(String email, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public User signUpUser(User newUser) throws Exception {


        if (newUser.getId() != null) {throw new Exception("New user should not have id. I WILL DECIDE IT!!!!");
        }
        String regexPattern =  "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isValidEmail = emailValidation(newUser.getEmail(), regexPattern);

        if (isValidEmail != true) {
            throw new Exception("Wrong email!!!!!");
        }

        User  storedUserEmail= userRepository.findByEmail(newUser.getEmail());

        if(storedUserEmail != null){
            throw new RuntimeException("EMAIL EXISTS!!!!");
        }

        User  storedUserUsername= userRepository.findByusername(newUser.getUsername());

        if(storedUserUsername != null){
            throw new RuntimeException("USERNAME EXISTS!!!!");
        }

            //code that gets user by id from database
            return userRepository.save(newUser);

    }

//   Update User
    public User updateUser(User requestUser, Long id) throws Exception {

        if (!requestUser.getId().equals(id)) {
            throw new Exception("ID DOESN'T MATCH!!!TRY AGAIN!");
        }
        User userfromDB = this.getUserById(id);

        userfromDB.setUsername(requestUser.getUsername());
        userfromDB.setEmail(requestUser.getEmail());
        userfromDB.setUsername(requestUser.getUsername());
        userfromDB.setPassword(requestUser.getPassword());
        userfromDB.setProfileimage(requestUser.getProfileimage());
        userfromDB.setEmail(requestUser.getEmail());
//    userfromDB.setDob(requestUser.Instant.now());
        return userRepository.save(requestUser);

    }
        public User getUserByEmail(String email) throws Exception {
            return userRepository.findByEmail1(email).orElseThrow(() -> new RestAppException(HttpStatus.NOT_FOUND, "ERROR_CODE_USER_NOT_FOUND", "User not found"));
        }


//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            User userToBeLoggedIn = null;
//            try {
//                userToBeLoggedIn = this.getUserByEmail(username);
//            } catch (Exception e) {
//                throw new UsernameNotFoundException("USER_NOT_FOUND", e);
//            }
//
//
//            return userRepository.save(requestUser);
        }
        //code that gets user by id from database







//    public ApartmentAttribute getAttributeByName(String name) throws Exception {
//        return apartmentAttributeRepository.findByName(name)
//                .orElseThrow(() -> new Exception("Attribute not found"));
//    }
package reddit.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reddit.restapi.models.Users;
import reddit.restapi.repositories.UserRepo;

import java.util.List;

@Service
public class UsersService {
    private UserRepo userRepository;

    @Autowired
    public UsersService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users createUser(Users user) throws Exception {
        if (user.getId() != null) {
            throw new Exception("New user should not have id. I WILL DECIDE IT!!!!");
        }
        //code that gets user by id from database
        return userRepository.save(user);
    }

}




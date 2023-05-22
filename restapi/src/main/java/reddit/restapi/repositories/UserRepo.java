package reddit.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
import reddit.restapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//manytomany
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

//   List <User> findByUsername1(String username);

    User findByusername(String username);
   User findByEmail(String email);
    public Optional<User> findByEmail1(String email);

    //@Query(nativeQuery = true, value = "select * from  reddit.restapi.user where username?1")
//public User findbyemail(tring username);
//    public Optional<User> findByEmail(String email);

//    @Query(nativeQuery = true,
//            value = "select * from bootcamp2304myrto.users where email = ?1 or username = ?1")
//    public Optional<User> findByEmailOrFirstname(String email);


}



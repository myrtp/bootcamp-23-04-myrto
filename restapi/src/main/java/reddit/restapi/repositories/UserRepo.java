package reddit.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import reddit.restapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    User findByusername(String username);
   User findByEmail(String email);

    @Query(nativeQuery = true,
            value = "select * from bootcamp2304myrto.users where username = ?1")
    public Optional<User> findAnyByUsername(String username);


}



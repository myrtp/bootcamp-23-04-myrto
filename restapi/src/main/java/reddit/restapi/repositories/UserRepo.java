package reddit.restapi.repositories;
import reddit.restapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
    public interface UserRepo extends JpaRepository<Users, Long>{
    public Users findByUsername(String username);
}


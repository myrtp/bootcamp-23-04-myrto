package reddit.restapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reddit.restapi.models.UserSubred;
import java.util.Optional;
public interface UserSubredRepo extends JpaRepository<UserSubred, Long> {

    @Query(nativeQuery = true,
            value = "select * from bootcamp2304myrto.user_subred where role = ?1")
    public Optional<UserSubred> findAnyByAuthority(String role);
}

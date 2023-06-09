package reddit.restapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reddit.restapi.models.UserSubred;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubredRepo extends JpaRepository<UserSubred, Long> {


    public List<UserSubred> findUserSubredBySubredditId(Long subredditId);
}

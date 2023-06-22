package reddit.restapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reddit.restapi.models.Subreddit;
import reddit.restapi.models.UserSubred;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubredRepo extends JpaRepository<UserSubred, Long> {


    public List<UserSubred> findUserSubredBySubredditId(Long subredditId);

    UserSubred findUserSubredBySubredditIdAndUserId(Long subredditId, Long userId);
    @Query("SELECT us FROM UserSubred us WHERE us.subredditId = :subredditId AND us.role = 'admin'")
    UserSubred findAdminBySubredditId(Long subredditId);

    @Query("SELECT us FROM UserSubred us WHERE us.subredditId = :subredditId AND us.role = 'member'")
    UserSubred findMemberBySubredditId(Long subredditId);

}

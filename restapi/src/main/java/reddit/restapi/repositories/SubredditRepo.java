package reddit.restapi.repositories;
import org.springframework.data.jpa.repository.Query;
import reddit.restapi.models.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reddit.restapi.models.UserSubred;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubredditRepo extends JpaRepository<Subreddit, Long> {


    @Query(nativeQuery = true,
            value = "select * from bootcamp2304myrto.subreddit where title = ?1")
    public Optional<Subreddit> findAnyByTitle(String title);


    Subreddit getSubredditById(Long id);


}


package reddit.restapi.repositories;

import reddit.restapi.models.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubredditRepo extends JpaRepository<Subreddit, Long> {


    List<Subreddit> findByTitle(String title);

}


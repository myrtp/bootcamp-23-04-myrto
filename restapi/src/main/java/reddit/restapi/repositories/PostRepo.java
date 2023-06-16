package reddit.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import reddit.restapi.models.Post;
import reddit.restapi.models.User;


import java.util.List;
import java.util.Optional;
@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    public List<Post> findPostsBySubredditId(Long subredditId);

    @Query(nativeQuery = true,
            value = "select * from bootcamp2304myrto.post order by created_at desc")
    public List<Post> sortPostsByCreationDate();

//    @Query(nativeQuery = true,
//            value = "select * from bootcamp2304myrto.post order by created_at ?1")
//    public List<Post> sortPostsByCreationDate(String orderBy);
    Post getPostById(Long id);
    @Query(nativeQuery = true,
            value = "select * from bootcamp2304myrto.post where user_id = ?1")
    public List<Post> findPostsByUserId(Long userId);

}

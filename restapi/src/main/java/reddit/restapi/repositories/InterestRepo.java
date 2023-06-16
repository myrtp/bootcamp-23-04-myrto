package reddit.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reddit.restapi.models.Interest;
@Repository
public interface InterestRepo extends JpaRepository<Interest, Long> {
    Interest getInterestById(Long id);
}

package reddit.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.Interest;
import reddit.restapi.repositories.InterestRepo;

import java.util.List;


@Service
public class InterestService {
    private InterestRepo interestRepo;

    @Autowired
    public InterestService(InterestRepo interestRepo) {
        this.interestRepo = interestRepo;

    }

    public List<Interest> getAllInterests() {
        return interestRepo.findAll();
    }

    public Interest getInterestById(Long id) throws Exception {
        return interestRepo.findById(id).orElseThrow(() -> new RestAppException(HttpStatus.NOT_FOUND,
                "ERROR_CODE_USER_NOT_FOUND", "Interest does not exist"));
    }
}

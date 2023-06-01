package reddit.restapi.util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.User;
import reddit.restapi.services.SubredditService;
import reddit.restapi.services.UsersService;

@Component
public class AuthorizationChecks {

    private UsersService usersService;
    private SubredditService subredditService;

    public AuthorizationChecks(UsersService usersService,SubredditService subredditService){
        this.usersService = usersService;
        this.subredditService = subredditService;
    }

    public boolean isTheSameUser(Long userId, Authentication authentication) throws Exception{
      User user = usersService.getUserfromAuthentication(authentication);
      if(user.getId() != userId){
          throw new RestAppException(HttpStatus.FORBIDDEN, "ERROR_CODE_FORBIDDEN", "FORBIDDEN RESOURCE!");
      }
        return true;
    }

}

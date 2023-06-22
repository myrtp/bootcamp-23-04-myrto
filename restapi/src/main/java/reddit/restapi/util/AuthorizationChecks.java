package reddit.restapi.util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.User;
import reddit.restapi.models.UserSubred;
import reddit.restapi.repositories.UserSubredRepo;
import reddit.restapi.services.SubredditService;
import reddit.restapi.services.UsersService;

import java.util.List;

@Component
public class AuthorizationChecks {

    private UsersService usersService;

    private UserSubredRepo userSubredditRepo;
    public AuthorizationChecks(UsersService usersService, UserSubredRepo userSubredditRepo){
        this.usersService = usersService;
        this.userSubredditRepo = userSubredditRepo;
    }

    public boolean isTheSameUser(Long userId, Authentication authentication) throws Exception{
      User user = usersService.getUserfromAuthentication(authentication);
      if(user.getId() != userId){
          throw new RestAppException(HttpStatus.FORBIDDEN, "ERROR_CODE_FORBIDDEN", "FORBIDDEN RESOURCE!");
      }
        return true;
    }

    public boolean isAdmin(Authentication authentication, Long subredditId) throws RestAppException {
        List<UserSubred> userSubreds = userSubredditRepo.findUserSubredBySubredditId(subredditId);
        Jwt userJwt = (Jwt) authentication.getPrincipal();
        Long userId = (Long) userJwt.getClaims().get("userId");

        for (UserSubred userSubred : userSubreds) {
            if (userSubred.getUser().getId().equals(userId) && userSubred.getRole().equals("admin")) {
                return true;
            }
        }

        throw new RestAppException(HttpStatus.UNAUTHORIZED, "ERROR_CODE_UNAUTHORIZED",
                "User does not have authority to make changes");
    }


    public boolean isMember(Authentication authentication, Long subredditId) {
        List<UserSubred> userSubreds = userSubredditRepo.findUserSubredBySubredditId(subredditId);
        Jwt userJwt = (Jwt) authentication.getPrincipal();
        Long userId = (Long) userJwt.getClaims().get("userId");

        for (UserSubred userSubred : userSubreds) {
            if (userSubred.getUser().getId().equals(userId) &&
                    (userSubred.getRole().equals("ADMIN") || userSubred.getRole().equals("MEMBER"))) {
                return true;
            }
        }

        return false; // User is not a member
    }

    public boolean isSubredditMember(Authentication authentication, Long subredditId) {
        Jwt userJwt = (Jwt) authentication.getPrincipal();
        Long userId = (Long) userJwt.getClaims().get("userId");

        UserSubred userSubred = userSubredditRepo.findUserSubredBySubredditIdAndUserId(subredditId, userId);
        return userSubred != null && (userSubred.getRole().equals("ADMIN") || userSubred.getRole().equals("MEMBER")); // User is a member
// User is not a member
    }


    public Long GetUserIDbyJWT(Authentication authentication){
        Jwt userJwt = (Jwt) authentication.getPrincipal();
        Long userId = (Long) userJwt.getClaims().get("userId");
        return userId;
    }

}

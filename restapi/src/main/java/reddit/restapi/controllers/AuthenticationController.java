package reddit.restapi.controllers;
import org.springframework.web.bind.annotation.RequestBody;
import reddit.restapi.exceptions.RestAppException;
import reddit.restapi.models.JwtResponseDTO;
import reddit.restapi.models.User;
import reddit.restapi.models.dtos.SignUpUserDTO;
import reddit.restapi.models.security.UserPrincipalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reddit.restapi.services.UsersService;
import reddit.restapi.util.SignUpDTOTransformer;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private SignUpDTOTransformer signUpDTOTransformer;

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public JwtResponseDTO login(Authentication authentication) {

        if (!(authentication.getPrincipal() instanceof UserPrincipalDTO)) {
            throw new RuntimeException("Authentication is null");
        }
        UserPrincipalDTO userPrincipalDTO = (UserPrincipalDTO) authentication.getPrincipal();
        Instant now = Instant.now();
        var claims = JwtClaimsSet.builder()
                .issuer("bootcamp-23-04")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(authentication.getName())
                .claim("username", authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .claim("a", "b")
                .claim("userId", userPrincipalDTO.getUserId())
                .build();

        String jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return JwtResponseDTO
                .builder()
                .token(jwt)
                .build();
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignUpUserDTO user) throws Exception {

        return usersService.signUpUser(user);

    }

}





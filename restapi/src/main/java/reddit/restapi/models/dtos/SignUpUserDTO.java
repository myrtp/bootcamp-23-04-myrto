package reddit.restapi.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SignUpUserDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private String username;
    private Instant dob;
    private String profileimage;
}

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
    public class UserDTO {

        private Long id;
        private String username;
        private String email;
        private String password;
        private String profileimage;
        private Instant dob;

    }


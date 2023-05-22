package reddit.restapi.exceptions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpringAppErrorMessageDTO {

    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;
    private Instant timestamp;

}

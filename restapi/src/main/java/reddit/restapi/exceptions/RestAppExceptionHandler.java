package reddit.restapi.exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Instant;

@ControllerAdvice
public class RestAppExceptionHandler extends ResponseEntityExceptionHandler{
     private Logger logger = LoggerFactory.getLogger(RestAppExceptionHandler.class);

    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {

        logger.error("Error:", ex);

        SpringAppErrorMessageDTO error = SpringAppErrorMessageDTO.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorCode("UNKNOWN_ERROR")
                .errorMessage(ex.getMessage())
                .timestamp(Instant.now())
                .build();

        if (ex instanceof RestAppException) {
            RestAppException restAppException = (RestAppException) ex;
            error = SpringAppErrorMessageDTO.builder()
                    .httpStatus(restAppException.getHttpStatus())
                    .errorCode(restAppException.getErrorCode())
                    .errorMessage(restAppException.getErrorMessage())
                    .timestamp(Instant.now())
                    .build();
        }




        return ResponseEntity
                .status(error.getHttpStatus())
                .body(error);
    }



    }






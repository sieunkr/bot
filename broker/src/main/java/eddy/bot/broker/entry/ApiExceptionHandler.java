package eddy.bot.broker.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Eddy Kim
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionPojo> unExpectedExceptionHandler (Exception e) {
        logger.error("API Exception (UnExpected Exception) - {}", e);

        return new ResponseEntity<>(new ExceptionPojo("UnExpected Exception", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

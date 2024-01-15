package CarRentalSpringBoot.carRentalExercise.aspects;

import CarRentalSpringBoot.carRentalExercise.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
public class RentaCarExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RentaCarExceptionHandler.class);

    @ExceptionHandler(value = {ClientIdNotFoundException.class, CarIdNotFoundException.class, RentalIdNotFoundException.class})
    public ResponseEntity<String> IdNotFoundHandler (Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(value = {ClientAlreadyExists.class, CarAlreadyExists.class, RentalAlreadyExists.class})
    public ResponseEntity<String> EntityAlreadyExistsHandler (Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED
        ).body(ex.getMessage());
    }
}




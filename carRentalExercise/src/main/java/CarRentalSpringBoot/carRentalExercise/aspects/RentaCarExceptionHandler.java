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

    @ExceptionHandler(value = {ClientIdNotFoundException.class})
    public ResponseEntity<String> ClientIdNotFoundHandler (Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("That Client Id doesn't exist.");
    }
    @ExceptionHandler(value = {CarIdNotFoundException.class})
    public ResponseEntity<String> CarIdNotFoundHandler (Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("That Car Id doesn't exist.");
    }

    @ExceptionHandler(value = {RentalIdNotFoundException.class})
    public ResponseEntity<String> RentalIdNotFoundHandler (Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("That Rental Id doesn't exist.");
    }
    @ExceptionHandler(value = {ClientAlreadyExists.class})
    public ResponseEntity<String> ClientAlreadyExistsHandler (Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Client already exists.");
    }
    @ExceptionHandler(value = {CarAlreadyExists.class})
    public ResponseEntity<String> CarAlreadyExistsHandler (Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Car already exists.");
    }
    @ExceptionHandler(value = {RentalAlreadyExists.class})
    public ResponseEntity<String> RentalAlreadyExistsHandler (Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Rental already exists.");
    }
}




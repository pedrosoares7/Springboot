package CarRentalSpringBoot.carRentalExercise.aspects;

import CarRentalSpringBoot.carRentalExercise.exceptions.*;
import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class RentaCarExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RentaCarExceptionHandler.class);

    @ExceptionHandler(value = {ClientIdNotFoundException.class})
    public ResponseEntity<String> ClientIdNotFoundHandler(Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.CLIENT_ID_NOT_EXISTS);
    }

    @ExceptionHandler(value = {CarIdNotFoundException.class})
    public ResponseEntity<String> CarIdNotFoundHandler(Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.CAR_ID_DOES_NOT_EXISTS);
    }

    @ExceptionHandler(value = {RentalIdNotFoundException.class})
    public ResponseEntity<String> RentalIdNotFoundHandler(Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Message.RENTAL_ID_DOES_NOT_EXISTS);
    }

    @ExceptionHandler(value = {ClientAlreadyExists.class})
    public ResponseEntity<String> ClientAlreadyExistsHandler(Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(Message.CLIENT_ID_ALREADY_EXISTS);
    }

    @ExceptionHandler(value = {CarAlreadyExists.class})
    public ResponseEntity<String> CarAlreadyExistsHandler(Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(Message.CAR_ID_ALREADY_EXISTS);
    }

    @ExceptionHandler(value = {RentalAlreadyExists.class})
    public ResponseEntity<String> RentalAlreadyExistsHandler(Exception ex) {
        logger.error("Known Exception: " + ex);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(Message.RENTAL_ID_ALREADY_EXISTS);
    }
}




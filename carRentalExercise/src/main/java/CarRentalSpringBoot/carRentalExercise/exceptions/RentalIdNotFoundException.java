package CarRentalSpringBoot.carRentalExercise.exceptions;

import lombok.Builder;


public class RentalIdNotFoundException extends Exception {
public RentalIdNotFoundException (String message){

    super (message);
}

}

package CarRentalSpringBoot.carRentalExercise.exceptions;

import lombok.Builder;


public class CarIdNotFoundException extends Exception {
    public CarIdNotFoundException (String message){

        super (message);
    }

}


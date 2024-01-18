package CarRentalSpringBoot.carRentalExercise.exceptions;

public class CarIsNotAvailableException extends Exception{
    public CarIsNotAvailableException (String message){

        super (message);
    }
}

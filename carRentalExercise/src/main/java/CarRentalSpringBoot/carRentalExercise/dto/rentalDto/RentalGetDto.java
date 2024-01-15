package CarRentalSpringBoot.carRentalExercise.dto.rentalDto;

import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientGetDto;

import java.time.LocalDate;

public record RentalGetDto (

        ClientGetDto client,

        CarGetDto car,

        double rentalPrice,

        LocalDate rentalStartDate,

        LocalDate rentalEndDate
){
}

package CarRentalSpringBoot.carRentalExercise.dto.rentalDto;

import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;

import java.time.LocalDate;

public record RentalCreateDto(


        Long carId,

        Long clientId,
        Long rentalPrice,

        LocalDate rentalStartDate,

        LocalDate rentalEndDate

) {
}
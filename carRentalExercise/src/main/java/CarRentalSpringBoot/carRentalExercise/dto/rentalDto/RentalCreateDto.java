package CarRentalSpringBoot.carRentalExercise.dto.rentalDto;

import java.time.LocalDate;


public record RentalCreateDto(

        Long carId,

        Long clientId,

        LocalDate rentalStartDate,

        LocalDate rentalEndDate


) {
}

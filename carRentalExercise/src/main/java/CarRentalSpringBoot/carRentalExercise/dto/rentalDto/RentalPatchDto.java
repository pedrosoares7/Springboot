package CarRentalSpringBoot.carRentalExercise.dto.rentalDto;

import java.time.LocalDate;

public record RentalPatchDto(

        Long Id,
     //   double rentalPrice,
        LocalDate rentalEndDate

) {
}

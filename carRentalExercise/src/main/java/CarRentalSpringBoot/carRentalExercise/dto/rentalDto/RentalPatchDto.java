package CarRentalSpringBoot.carRentalExercise.dto.rentalDto;

import jakarta.validation.Valid;

import java.time.LocalDate;

public record RentalPatchDto(

        Long Id,

        @Valid
        LocalDate rentalEndDate



) {
}

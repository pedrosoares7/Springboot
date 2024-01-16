package CarRentalSpringBoot.carRentalExercise.dto.carDto;

import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CarPatchDto(

        @Valid
        int km,

        @Valid
        double dailyRentalPrice
) {

}

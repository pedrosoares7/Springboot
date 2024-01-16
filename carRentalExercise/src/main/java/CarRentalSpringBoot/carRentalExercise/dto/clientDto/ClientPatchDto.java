package CarRentalSpringBoot.carRentalExercise.dto.clientDto;


import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


public record ClientPatchDto(

        @Valid
       @Pattern(regexp = "[a-zA-Z]", message = "Please insert some letters.")
        String name,

        @Valid
        String email
) {
}

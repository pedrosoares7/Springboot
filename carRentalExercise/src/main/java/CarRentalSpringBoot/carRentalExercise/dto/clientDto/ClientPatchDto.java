package CarRentalSpringBoot.carRentalExercise.dto.clientDto;

import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ClientPatchDto(

       // @Valid
    //    @NotBlank(message = Message.NAME_MANDATORY)
        String name,
      //  @Valid
      //  @NotBlank(message = "Email is mandatory")
        String email
) {
}

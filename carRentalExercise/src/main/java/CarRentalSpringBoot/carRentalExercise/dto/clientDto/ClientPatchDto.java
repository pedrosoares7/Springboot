package CarRentalSpringBoot.carRentalExercise.dto.clientDto;

import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientPatchDto(


        @NotNull(message = Message.NAME_MANDATORY)
        String name,

        @NotNull(message = Message.EMAIL_MANDATORY)
        String email
) {
}

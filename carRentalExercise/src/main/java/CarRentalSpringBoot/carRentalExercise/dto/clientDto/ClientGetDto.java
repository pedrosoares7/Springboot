package CarRentalSpringBoot.carRentalExercise.dto.clientDto;

import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClientGetDto(

        @Valid
        @NotBlank(message = Message.NAME_MANDATORY)
        String name,
        @Valid
        @NotBlank(message = Message.NIF_MANDATORY)
        int nif

) {

}

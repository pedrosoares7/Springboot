package CarRentalSpringBoot.carRentalExercise.dto.clientDto;

import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record ClientGetDto(


        @NotBlank(message = Message.NAME_MANDATORY)
        @Pattern(regexp = "^[a-zA-Z ]+$")
        String name,

        @NotBlank(message = Message.NIF_MANDATORY)
        @Min(value = 111111111, message = "Insert a valid NIF")
        @Max(value = 999999999, message = "Insert a valid NIF")
        int nif

) {

}

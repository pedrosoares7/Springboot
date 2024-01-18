package CarRentalSpringBoot.carRentalExercise.dto.clientDto;


import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;


public record ClientPatchDto(


        @Pattern(regexp = "^[a-zA-Z ]+$")
        String name,

        @Email(message = "Enter a valid Email")
        @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
        String email
) {
}

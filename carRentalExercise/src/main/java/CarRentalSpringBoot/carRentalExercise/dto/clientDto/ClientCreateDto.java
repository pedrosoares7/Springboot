package CarRentalSpringBoot.carRentalExercise.dto.clientDto;

import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientCreateDto(



        @NotNull(message = Message.NAME_MANDATORY)
        @Pattern(regexp = "^([ \u00c0-\u01ffa-zA-Z'\\-])+$")
        String name,


        @Email(message = "Enter a valid Email")
        @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
               + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Invalid email")
        String email,

        @NotNull(message = Message.DRIVER_LICENSE_MANDATORY)
        @Min(value = 111111111)
        @Max(value = 999999999)
        int driversLicense,

        @Min(value = 111111111, message = "Insert a valid NIF")
        @Max(value = 999999999, message = "Insert a valid NIF")
        @NotNull(message = Message.NIF_MANDATORY)
        int nif,


        @Past(message = Message.DATE_OF_BIRTH_MANDATORY)
        LocalDate dateOfBirth

) {

}

package CarRentalSpringBoot.carRentalExercise.dto.clientDto;

import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClientCreateDto(

     Long Id,

     @NotNull(message = Message.NAME_MANDATORY)
     String name,

     @NotNull(message = Message.EMAIL_MANDATORY)
     String email,

     @NotNull(message = Message.DRIVER_LICENSE_MANDATORY)
     int driversLicense,

     @NotNull(message = Message.NIF_MANDATORY)
     int nif,


     @NotNull(message = Message.DATE_OF_BIRTH_MANDATORY)
     LocalDate dateOfBirth

) {

}

package CarRentalSpringBoot.carRentalExercise.dto.clientDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClientCreateDto(

     Long Id,
     @Valid
     @NotBlank(message = "Name is mandatory")
     String name,
     @Valid
     @NotBlank(message = "Email is mandatory")
     String email,
     @Valid
     @NotBlank(message = "DriversLicense is mandatory")
     int driversLicense,
     @Valid
     @NotBlank(message = "NIF is mandatory")
     int nif,
     @Valid
     @NotBlank(message = "DateOfBirth is mandatory")
     LocalDate dateOfBirth

) {

}

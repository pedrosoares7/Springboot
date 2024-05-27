package CarRentalSpringBoot.carRentalExercise.dto.rentalDto;

import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientGetDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RentalGetDto(

        Long id,

        ClientGetDto client,

        CarGetDto car,

        double rentalPrice,

        LocalDate rentalStartDate,

        LocalDate rentalEndDate,

        boolean isRentalTerminated
) {


}

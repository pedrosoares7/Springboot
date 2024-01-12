package CarRentalSpringBoot.carRentalExercise.dto.carDto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CarCreateDto(
        Long Id,
        @Valid
        @NotBlank(message = "Brand is mandatory")
        String brand,
        @Valid
        @NotBlank(message = "Plate is mandatory")
        String plate,
        @Valid
        @NotBlank(message = "HorsePower is mandatory")
        int horsePower,
        @Valid
        @NotBlank(message = "Km is mandatory")
        int km
) {

}

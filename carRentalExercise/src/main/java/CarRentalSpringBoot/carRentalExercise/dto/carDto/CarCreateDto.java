package CarRentalSpringBoot.carRentalExercise.dto.carDto;


import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.constraints.NotNull;

public record CarCreateDto(

        @NotNull(message = Message.CAR_BRAND_MANDATORY)
        String brand,

        @NotNull(message = Message.LICENSE_PLATE_MANDATORY)
        String plate,

        @NotNull(message = Message.HORSE_POWER_MANDATORY)
        int horsePower,

        @NotNull(message = Message.KM_MANDATORY)
        int km,


        @NotNull(message = Message.DAILY_RENTAL_PRICE_MANDATORY)
        double dailyRentalPrice

) {

}

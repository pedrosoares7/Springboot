package CarRentalSpringBoot.carRentalExercise.dto.carDto;

public record CarGetDto (

        String brand,
        String plate,

        double dailyRentalPrice,

        boolean available){
}

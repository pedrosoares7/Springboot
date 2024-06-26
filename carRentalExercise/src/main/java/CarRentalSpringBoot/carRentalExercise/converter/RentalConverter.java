package CarRentalSpringBoot.carRentalExercise.converter;


import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;

public class RentalConverter {

    public static RentalCreateDto fromEntityRentalToDto(Rental rental) {
        return new RentalCreateDto(

                rental.getClient().getId(),
                rental.getCar().getId(),
                rental.getRentalStartDate(),
                rental.getRentalEndDate()


        );
    }

    public static RentalGetDto fromEntityToRentalGetDto(Rental rental) {
        return new RentalGetDto(
                rental.getId(),
                ClientConverter.fromEntityToGetDto(rental.getClient()),
                CarConverter.fromEntityToCarGetDto(rental.getCar()),
                rental.getRentalPrice(),
                rental.getRentalStartDate(),
                rental.getRentalEndDate(),
                rental.isRentalTerminated()

        );
    }

}

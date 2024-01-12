package CarRentalSpringBoot.carRentalExercise.converter;

import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;

public class RentalConverter {



    public static RentalCreateDto fromEntityRentalToDto(Rental rental) {
        return new RentalCreateDto(

                rental.getCar().getId(),
                rental.getClient().getId(),
                rental.getRentalPrice(),
                rental.getRentalStartDate(),
                rental.getRentalEndDate()
        );
    }

    public static Rental fromDtoToEntityRental(RentalCreateDto rentalDto) {
        return Rental.builder()
                .rentalPrice(rentalDto.rentalPrice())
                .rentalStartDate(rentalDto.rentalStartDate())
                .rentalEndDate(rentalDto.rentalEndDate())
                .build();

    }
}

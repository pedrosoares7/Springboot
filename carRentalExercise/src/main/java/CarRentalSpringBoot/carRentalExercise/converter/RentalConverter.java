package CarRentalSpringBoot.carRentalExercise.converter;

import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;

public class RentalConverter {


    public static RentalCreateDto fromEntityRentalToDto(Rental rental) {
        return new RentalCreateDto(

                rental.getCar().getId(),
                rental.getClient().getId(),
              //  rental.getRentalPrice(),
                rental.getRentalStartDate(),
                rental.getRentalEndDate()
        );
    }

    public static RentalGetDto fromEntityToRentalGetDto(Rental rental) {
        return new RentalGetDto(

                ClientConverter.fromEntityGetToDto(rental.getClient()),
                CarConverter.fromEntityToCarGetToDto(rental.getCar()),
                rental.getRentalPrice(),
                rental.getRentalStartDate(),
                rental.getRentalEndDate()
        );
    }

    public static Rental fromDtoToEntityRental(RentalCreateDto rentalDto) {
        return Rental.builder()
             //   .rentalPrice(rentalDto.rentalPrice())
                .rentalStartDate(rentalDto.rentalStartDate())
                .rentalEndDate(rentalDto.rentalEndDate())
                .build();

    }
}

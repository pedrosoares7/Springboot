package CarRentalSpringBoot.carRentalExercise.mapper;

import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    RentalCreateDto fromEntityRentalToDto(Rental rental);

    RentalGetDto fromEntityToRentalGetDto(Rental rental);

    Rental fromDtoToEntityRental (RentalCreateDto rentalDto);
}

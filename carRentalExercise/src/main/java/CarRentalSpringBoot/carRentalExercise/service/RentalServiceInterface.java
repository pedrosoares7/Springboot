package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;

import java.util.List;

public interface RentalServiceInterface {
    List<RentalGetDto> getRentalSomeInfo();

    Rental getRental(Long rentalId);

    List<RentalCreateDto> getRentals();

    void addNewRental(RentalCreateDto rental);

    void updateRental(Long id, RentalPatchDto rental);
}

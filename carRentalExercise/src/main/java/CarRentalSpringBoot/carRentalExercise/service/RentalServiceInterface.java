package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;

import java.util.List;

public interface RentalServiceInterface {
    List<RentalCreateDto> getRentals();

    Rental getRental(Long rentalId);

    void addNewRental(RentalCreateDto rental);

    void updateRental(Long id, RentalPatchDto rental);
}

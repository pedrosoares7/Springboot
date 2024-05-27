package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIsNotAvailableException;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.RentalIdNotFoundException;

import java.util.List;

public interface RentalServiceInterface {
    List<RentalGetDto> getRentalSomeInfo();

    Rental getRental(Long rentalId) throws RentalIdNotFoundException;

    List<RentalCreateDto> getRentals();

    RentalGetDto addNewRental(RentalCreateDto rental) throws RentalIdNotFoundException, CarIdNotFoundException, ClientIdNotFoundException, CarIsNotAvailableException;

    void updateRental(Long id, RentalPatchDto rental) throws RentalIdNotFoundException;
}

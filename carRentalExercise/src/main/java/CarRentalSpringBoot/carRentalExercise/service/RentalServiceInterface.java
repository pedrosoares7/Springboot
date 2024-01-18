package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.exceptions.*;

import java.util.List;

public interface RentalServiceInterface {
    List<RentalGetDto> getRentalSomeInfo();

    Rental getRental(Long rentalId) throws RentalIdNotFoundException;

    List<RentalCreateDto> getRentals();

    Rental addNewRental(RentalCreateDto rental) throws RentalIdNotFoundException, AppExceptions, CarIdNotFoundException, ClientIdNotFoundException, CarIsNotAvailableException;

    void updateRental(Long id, RentalPatchDto rental) throws AppExceptions, RentalIdNotFoundException;
}

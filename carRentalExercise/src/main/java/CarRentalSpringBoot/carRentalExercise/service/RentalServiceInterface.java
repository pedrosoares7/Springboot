package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.exceptions.AppExceptions;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.RentalIdNotFoundException;

import java.util.List;

public interface RentalServiceInterface {
    List<RentalGetDto> getRentalSomeInfo();

    Rental getRental(Long rentalId) throws RentalIdNotFoundException;

    List<RentalCreateDto> getRentals();

    void addNewRental(RentalCreateDto rental) throws RentalIdNotFoundException, AppExceptions, CarIdNotFoundException, ClientIdNotFoundException;

    void updateRental(Long id, RentalPatchDto rental) throws AppExceptions, RentalIdNotFoundException;
}

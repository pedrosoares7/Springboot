package CarRentalSpringBoot.carRentalExercise.service;


import CarRentalSpringBoot.carRentalExercise.converter.RentalConverter;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIsNotAvailableException;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.RentalIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.repository.RentalRepository;
import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService implements RentalServiceInterface {

    private final RentalRepository rentalRepository;

    private final CarService carService;

    private final ClientService clientService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, CarService carService, ClientService clientService) {
        this.rentalRepository = rentalRepository;
        this.carService = carService;
        this.clientService = clientService;
    }


    @Override
    public List<RentalGetDto> getRentalSomeInfo() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(RentalConverter::fromEntityToRentalGetDto)
                .toList();
    }

    @Override
    public List<RentalCreateDto> getRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(RentalConverter::fromEntityRentalToDto)
                .toList();
    }

    @Override
    public Rental getRental(Long rentalId) throws RentalIdNotFoundException {
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);
        if (rentalOptional.isEmpty()) {
            throw new RentalIdNotFoundException(rentalId + Message.RENTAL_ID_DOES_NOT_EXISTS);
        }
        return rentalOptional.get();
    }

    @Override
    public RentalGetDto addNewRental(RentalCreateDto rental) throws CarIdNotFoundException, ClientIdNotFoundException, CarIsNotAvailableException {
        Car newCar = carService.getCar(rental.carId());
        if (!newCar.isAvailable()) {
            throw new CarIsNotAvailableException(rental.carId() + Message.CAR_IS_NOT_AVAILABLE);
        }
        Client newClient = clientService.getClient(rental.clientId());
        LocalDate starRentalDate = rental.rentalStartDate();
        LocalDate endRentalDate = rental.rentalEndDate();
        Rental newRental = new Rental(newCar, newClient, starRentalDate, endRentalDate);
        newRental.getCar().setAvailable(false);
        Rental rentalToSave = rentalRepository.save(newRental);
        return RentalConverter.fromEntityToRentalGetDto(rentalToSave);

    }

    @Override
    public void updateRental(Long id, RentalPatchDto rental) throws RentalIdNotFoundException {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        if (!rentalOptional.isPresent()) {
            throw new RentalIdNotFoundException(id + Message.RENTAL_ID_DOES_NOT_EXISTS);
        }
        Rental rentalToUpdate = rentalOptional.get();
        if (rental.rentalEndDate() != null && !rental.rentalEndDate().equals(rentalToUpdate.getRentalEndDate())) {
            rentalToUpdate.setRentalEndDate(rental.rentalEndDate());
            rentalToUpdate.setRentalPrice();
        }

        rentalToUpdate.setRentalTerminated(rental.isRentalTerminated());
        if (rentalToUpdate.isRentalTerminated()) {
            rentalToUpdate.getCar().setAvailable(true);
        }
        rentalRepository.save(rentalToUpdate);
    }
}

package CarRentalSpringBoot.carRentalExercise.service;


import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.RentalIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.mapper.RentalMapper;
import CarRentalSpringBoot.carRentalExercise.repository.RentalRepository;
import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService implements RentalServiceInterface {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private ClientService clientService;

    @Autowired
    private RentalMapper rentalMapper;
    @Override
    public List<RentalGetDto> getRentalSomeInfo() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(rentalMapper::fromEntityToRentalGetDto)
                .toList();
    }
    @Override
    public List<RentalCreateDto> getRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(rentalMapper::fromEntityRentalToDto)
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
    public void addNewRental(RentalCreateDto rental) throws CarIdNotFoundException, ClientIdNotFoundException {
        Car newCar = carService.getCar(rental.carId());
        Client newClient = clientService.getClient(rental.clientId());
        LocalDate starRentalDate = rental.rentalStartDate();
        LocalDate endRentalDate = rental.rentalEndDate();
        Rental newRental = new Rental(newCar,newClient, starRentalDate,endRentalDate);
        rentalRepository.save(newRental);

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
        }
      /*  if (rental.rentalPrice() < 0 && rental.rentalPrice() !=(rentalToUpdate.getRentalPrice())) {
            rentalToUpdate.setRentalPrice(rental.rentalPrice());
        }*/
       rentalRepository.save(rentalToUpdate);
    }
}

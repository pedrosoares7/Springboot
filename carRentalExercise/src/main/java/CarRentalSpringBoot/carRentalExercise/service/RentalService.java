package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.converter.RentalConverter;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<RentalCreateDto> getRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(RentalConverter::fromEntityRentalToDto)
                .toList();
    }

    @Override
    public Rental getRental(Long rentalId) {
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);
        if (rentalOptional.isEmpty()) {
            throw new IllegalStateException("The rental Id " + rentalId + " does not exist.");
        }
        return rentalOptional.get();
    }

    @Override
    public void addNewRental(RentalCreateDto rental) {
        Car newCar = carService.getCar(rental.carId());
        Client newClient = clientService.getClient(rental.clientId());
        Rental newRental = new Rental(newCar,newClient);
        newRental.setRentalStartDate(rental.rentalStartDate());
        newRental.setRentalPrice(rental.rentalPrice());
        newRental.setRentalEndDate(rental.rentalEndDate());
        rentalRepository.save(newRental);

    }

    @Override
    public void updateRental(Long id, RentalPatchDto rental) {
        Optional<Rental> rentalOptional = rentalRepository.findById(id);
        if (!rentalOptional.isPresent()) {
            throw new IllegalStateException("Rental with id " + id + "does not exist");
        }
        Rental rentalToUpdate = rentalOptional.get();
        if (rental.rentalEndDate() != null && !rental.rentalEndDate().equals(rentalToUpdate.getRentalEndDate())) {
            rentalToUpdate.setRentalEndDate(rental.rentalEndDate());
        }
        if (rental.rentalPrice() < 0 && rental.rentalPrice()!=rentalToUpdate.getRentalPrice()) {
            rentalToUpdate.setRentalPrice(rental.rentalPrice());
        }
       rentalRepository.save(rentalToUpdate);
    }
}

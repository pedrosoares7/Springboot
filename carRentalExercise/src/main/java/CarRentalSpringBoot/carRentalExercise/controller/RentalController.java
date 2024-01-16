package CarRentalSpringBoot.carRentalExercise.controller;

import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarPatchDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.exceptions.RentalIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/")//todos os rentals
    public ResponseEntity<List<RentalGetDto>> getRentals(){
        return new ResponseEntity<>(rentalService.getRentalSomeInfo(), HttpStatus.OK);
    }

    @GetMapping("/{rentalId}")//rental por ID
    public ResponseEntity<Rental> getRental (@PathVariable("rentalId") Long rentalId) throws RentalIdNotFoundException {
        return new ResponseEntity<>(rentalService.getRental(rentalId), HttpStatus.OK);
}
    @PostMapping("/")
    public ResponseEntity<Rental>addNewRental(@Valid @RequestBody RentalCreateDto rental) throws CarIdNotFoundException, ClientIdNotFoundException {
        return new ResponseEntity<>(rentalService.addNewRental(rental), HttpStatus.CREATED);
    }
    @PatchMapping(path = "{rentalId}")
    public ResponseEntity<Rental> updateRental(@PathVariable("rentalId") Long id,
                                               @Valid @RequestBody RentalPatchDto rental) throws RentalIdNotFoundException {
        rentalService.updateRental(id, rental);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

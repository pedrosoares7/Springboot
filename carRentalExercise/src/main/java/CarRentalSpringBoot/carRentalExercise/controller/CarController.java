package CarRentalSpringBoot.carRentalExercise.controller;

import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarPatchDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarAlreadyExists;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {


    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CarCreateDto>> getCars(){
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }

    @GetMapping("/{carId}")//car por ID
    public ResponseEntity<Car> getCar (@PathVariable("carId") Long carId) throws CarIdNotFoundException {
        return new ResponseEntity<>(carService.getCar(carId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Car>addNewCar(@Valid @RequestBody CarCreateDto car) throws CarAlreadyExists {
        carService.addNewCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping(path = "{carId}")
    public ResponseEntity<Client> updateCar(@PathVariable("carId") Long id, @Valid @RequestBody CarPatchDto car) throws CarIdNotFoundException {
        carService.updateCar(id, car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "{carId}")
    public ResponseEntity<Car> changeCar(@PathVariable("carId") Long id, Car car) throws CarIdNotFoundException {
        carService.changeCar(id, car);
        car.setId(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

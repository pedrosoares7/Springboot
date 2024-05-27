package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarAlreadyExists;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIdNotFoundException;

import java.util.List;

public interface CarServiceInterface {
    List<CarCreateDto> getCars();

    Car getCar(Long carId) throws CarIdNotFoundException;

    CarCreateDto getCarCreateDto(Long carId) throws CarIdNotFoundException;

    CarCreateDto addNewCar(CarCreateDto car) throws CarAlreadyExists;

    CarGetDto updateCar(Long id, CarPatchDto car) throws CarIdNotFoundException;

    CarGetDto changeCar(Long id, Car car) throws CarIdNotFoundException;
}

package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;

import java.util.List;

public interface CarServiceInterface {
    List<CarCreateDto> getCars();

    Car getCar(Long carId);

    CarCreateDto getCarCreateDto(Long carId);

    void addNewCar(CarCreateDto car);

    void updateCar(Long id, CarPatchDto car);

    void changeCar(Long id, Car car);
}

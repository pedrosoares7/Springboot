package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.converter.CarConverter;
import CarRentalSpringBoot.carRentalExercise.converter.ClientConverter;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }



    @Override
    public List<CarCreateDto> getCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(CarConverter::fromEntityToDto)
                .toList();

    }

    @Override
    public Car getCar(Long carId) {
        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isEmpty()) {
            throw new IllegalStateException("The car Id " + carId + " does not exist.");
        }
        return carOptional.get();
    }


    @Override
    public void addNewCar(CarCreateDto car) {
        Optional<Car> carOptional = this.carRepository.findByPlate(car.plate());
        if (carOptional.isPresent())
            throw new IllegalStateException("email taken");
        Car newCar = CarConverter.fromDtoToEntity(car);
        carRepository.save(newCar);
    }


    @Override
    public void updateCar(Long id, CarPatchDto car) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (!carOptional.isPresent()) {
            throw new IllegalStateException("Car with id " + id + " does not exist");
        }
        Car carToUpdate = carOptional.get();

        carToUpdate.setKm(car.km());

        carRepository.save(carToUpdate);


    }


    @Override
    public void changeCar(Long id, Car car) {
        car.setId(id);
        Optional<Car> carOptional = carRepository.findById(id);
        if (!carOptional.isPresent()) {
            throw new IllegalStateException("Car with id " + id + " does not exist");
        }
        carRepository.save(car);

    }
}

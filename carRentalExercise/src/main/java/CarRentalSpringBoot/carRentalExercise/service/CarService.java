package CarRentalSpringBoot.carRentalExercise.service;


import CarRentalSpringBoot.carRentalExercise.converter.CarConverter;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarGetDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarAlreadyExists;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.repository.CarRepository;
import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
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
    public CarCreateDto getCarCreateDto(Long carId) throws CarIdNotFoundException {
        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isEmpty()) {
            throw new CarIdNotFoundException(carId + Message.CAR_ID_DOES_NOT_EXISTS);
        }
        Car car = carOptional.get();
        return CarConverter.fromEntityToDto(car);
    }

    @Override
    public Car getCar(Long carId) throws CarIdNotFoundException {
        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isEmpty()) {
            throw new CarIdNotFoundException(carId + Message.CAR_ID_DOES_NOT_EXISTS);
        }
        return carOptional.get();
    }


    @Override
    public CarCreateDto addNewCar(CarCreateDto car) throws CarAlreadyExists {
        Optional<Car> carOptional = this.carRepository.findByPlate(car.plate());
        if (carOptional.isPresent())
            throw new CarAlreadyExists(car + Message.PLATE_ALREADY_TAKEN);
        Car newCar = CarConverter.fromDtoToEntity(car);
        carRepository.save(newCar);
        return car;
    }


    @Override
    public CarGetDto updateCar(Long id, CarPatchDto car) throws CarIdNotFoundException {
        Optional<Car> carOptional = carRepository.findById(id);
        if (!carOptional.isPresent()) {
            throw new CarIdNotFoundException(id + Message.CAR_ID_DOES_NOT_EXISTS);
        }
        Car carToUpdate = carOptional.get();

        carToUpdate.setKm(car.km());
        carToUpdate.setDailyRentalPrice(car.dailyRentalPrice());

        return CarConverter.fromEntityToCarGetDto(carRepository.save(carToUpdate));


    }


    @Override
    public CarGetDto changeCar(Long id, Car car) throws CarIdNotFoundException {
        car.setId(id);
        Optional<Car> carOptional = carRepository.findById(id);
        if (!carOptional.isPresent()) {
            throw new CarIdNotFoundException(id + Message.CAR_ID_DOES_NOT_EXISTS);
        }
        return CarConverter.fromEntityToCarGetDto(carRepository.save(car));

    }
}

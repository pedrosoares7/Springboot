package CarRentalSpringBoot.carRentalExercise.service;



import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarAlreadyExists;
import CarRentalSpringBoot.carRentalExercise.exceptions.CarIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.mapper.CarMapper;
import CarRentalSpringBoot.carRentalExercise.repository.CarRepository;
import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<CarCreateDto> getCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(carMapper::fromEntityToDto)
                .toList();

    }

    @Override
    public CarCreateDto getCarCreateDto(Long carId) throws CarIdNotFoundException {
        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isEmpty()) {
            throw new CarIdNotFoundException(carId + Message.CAR_ID_DOES_NOT_EXISTS);
        }
        Car car = carOptional.get();
        return carMapper.fromEntityToDto(car);
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
    public Car addNewCar(CarCreateDto car) throws CarAlreadyExists {
        Optional<Car> carOptional = this.carRepository.findByPlate(car.plate());
        if (carOptional.isPresent())
            throw new CarAlreadyExists(car + Message.PLATE_ALREADY_TAKEN);
        Car newCar = carMapper.fromDtoToEntity(car);
        return carRepository.save(newCar);
    }


    @Override
    public void updateCar(Long id, CarPatchDto car) throws CarIdNotFoundException {
        Optional<Car> carOptional = carRepository.findById(id);
        if (!carOptional.isPresent()) {
            throw new CarIdNotFoundException(id + Message.CAR_ID_DOES_NOT_EXISTS);
        }
        Car carToUpdate = carOptional.get();

        carToUpdate.setKm(car.km());
        carToUpdate.setDailyRentalPrice(car.dailyRentalPrice());

        carRepository.save(carToUpdate);


    }


    @Override
    public void changeCar(Long id, Car car) throws CarIdNotFoundException {
        car.setId(id);
        Optional<Car> carOptional = carRepository.findById(id);
        if (!carOptional.isPresent()) {
            throw new CarIdNotFoundException(id + Message.CAR_ID_DOES_NOT_EXISTS);
        }
        carRepository.save(car);

    }
}

package CarRentalSpringBoot.carRentalExercise.converter;

import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarGetDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;

public class CarConverter {
    public static CarCreateDto fromEntityToDto(Car cars) {
        return new CarCreateDto(
                cars.getBrand(),
                cars.getPlate(),
                cars.getHorsePower(),
                cars.getKm(),
                cars.getDailyRentalPrice()


        );
    }

    public static CarGetDto fromEntityToCarGetDto(Car cars) {
        return new CarGetDto(
                cars.getBrand(),
                cars.getPlate(),
                cars.getDailyRentalPrice(),
                cars.isAvailable()
        );
    }


    public static Car fromDtoToEntity(CarCreateDto carsDto) {
        return Car.builder()
                .brand(carsDto.brand())
                .plate(carsDto.plate())
                .horsePower(carsDto.horsePower())
                .km(carsDto.km())
                .dailyRentalPrice(carsDto.dailyRentalPrice())
                .isAvailable(true)
                .build();

    }
}

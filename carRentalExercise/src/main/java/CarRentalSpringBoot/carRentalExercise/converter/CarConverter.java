package CarRentalSpringBoot.carRentalExercise.converter;


import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;

import java.time.LocalDate;

public class CarConverter {
    public static CarCreateDto fromEntityToDto(Car cars) {
        return new CarCreateDto(
                cars.getId(),
                cars.getBrand(),
                cars.getPlate(),
                cars.getHorsePower(),
                cars.getKm()
        );
    }

    public static Car fromDtoToEntity(CarCreateDto carsDto) {
        return Car.builder()
                .brand(carsDto.brand())
                .plate(carsDto.plate())
                .horsePower(carsDto.horsePower())
                .km(carsDto.km())
                .build();
    }
    //public static Car from
}

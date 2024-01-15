package CarRentalSpringBoot.carRentalExercise.mapper;

import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.carDto.CarGetDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarCreateDto fromEntityToDto(Car cars);

    CarGetDto fromEntityToCarGetToDto (Car cars);


    Car fromDtoToEntity (CarCreateDto carsDto);
}

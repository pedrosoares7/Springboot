package CarRentalSpringBoot.carRentalExercise.mapper;

import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientGetDto;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientCreateDto fromEntityToDto (Client client);

    ClientGetDto fromEntityGetToDto (Client client);

    Client fromDtoToEntity (ClientCreateDto clientDto);
}

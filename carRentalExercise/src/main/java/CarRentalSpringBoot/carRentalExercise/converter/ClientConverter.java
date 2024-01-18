package CarRentalSpringBoot.carRentalExercise.converter;

import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientGetDto;
import CarRentalSpringBoot.carRentalExercise.entity.Client;

public class ClientConverter {

    public static ClientCreateDto fromEntityToDto(Client client) {

        return new ClientCreateDto(
                client.getName(),
                client.getEmail(),
                client.getDriversLicense(),
                client.getNif(),
                client.getDateOfBirth()
        );
    }

    public static ClientGetDto fromEntityGetToDto(Client client) {
        return new ClientGetDto(
                client.getName(),
                client.getNif()
        );
    }

    public static Client fromDtoToEntity(ClientCreateDto clientDto) {
        return Client.builder()
                .name(clientDto.name())
                .email(clientDto.email())
                .driversLicense(clientDto.driversLicense())
                .nif(clientDto.nif())
                .dateOfBirth(clientDto.dateOfBirth())
                .build();
    }


}

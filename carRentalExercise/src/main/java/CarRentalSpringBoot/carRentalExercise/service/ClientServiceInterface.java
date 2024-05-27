package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientAlreadyExists;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientIdNotFoundException;

import java.util.List;


public interface ClientServiceInterface {

    List<ClientCreateDto> getClients();

    Client getClient(Long clientId) throws ClientIdNotFoundException;

    ClientCreateDto getClientCreateDto(Long clientId) throws ClientIdNotFoundException;

    ClientCreateDto addNewClient(ClientCreateDto client) throws ClientAlreadyExists;

    void updateClient(Long id, ClientPatchDto client) throws ClientIdNotFoundException;

    void deleteClient(Long clientId) throws ClientIdNotFoundException;

    void changeClient(Long id, Client client) throws ClientIdNotFoundException;
}

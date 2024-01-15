package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.exceptions.AppExceptions;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientAlreadyExists;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientIdNotFoundException;

import java.util.List;

public interface ClientServiceInterface {
    /* public List<ClientCreateDto> getClient() {
             List<Client> clients = clientRepository.findAll();
             return clients.stream()
                     .map(ClientConverter::fromEntityToDto)
                     .toList();

                   //  from client list to client dto list
         }*/
    List<ClientCreateDto> getClients();

   Client getClient(Long clientId) throws AppExceptions, ClientIdNotFoundException;

   ClientCreateDto getClientCreateDto (Long clientId) throws ClientIdNotFoundException;

    void addNewClient(ClientCreateDto client) throws ClientAlreadyExists;

    void updateClient(Long id, ClientPatchDto client) throws ClientIdNotFoundException, ClientAlreadyExists;

    void deleteClient(Long clientId) throws ClientIdNotFoundException;

    void changeClient(Long id, Client client) throws ClientIdNotFoundException;
}

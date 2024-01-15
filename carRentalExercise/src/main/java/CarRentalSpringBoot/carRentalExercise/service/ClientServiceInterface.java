package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Client;

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

   Client getClient(Long clientId);

   ClientCreateDto getClientCreateDto (Long clientId);

    void addNewClient(ClientCreateDto client);

    void updateClient(Long id, ClientPatchDto client);

    void deleteClient(Long clientId);

    void changeClient(Long id, Client client);
}

package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.converter.ClientConverter;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientServiceInterface {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


   /* public List<ClientCreateDto> getClient() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(ClientConverter::fromEntityToDto)
                .toList();

              //  from client list to client dto list
    }*/
   @Override
   public List<ClientCreateDto> getClients() {
       List<Client> clients = clientRepository.findAll();
       return clients.stream()
               .map(ClientConverter::fromEntityToDto)
               .toList();
      // return clientRepository.findAll();
   }

    @Override
    public Client getClient(Long carId) {
        Optional<Client> clientOptional = clientRepository.findById(carId);
        if (clientOptional.isEmpty()) {
            throw new IllegalStateException("The car Id " + carId + " does not exist.");
        }
        return clientOptional.get();
    }




    @Override
    public void addNewClient(ClientCreateDto client) {
        Optional<Client> clientOptional = this.clientRepository.findByEmail(client.email());
        if (clientOptional.isPresent())
            throw new IllegalStateException("email taken");
        Client newClient = ClientConverter.fromDtoToEntity(client);
        clientRepository.save(newClient);
    }

    @Override
    public void updateClient(Long id, ClientPatchDto client) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) {
            throw new IllegalStateException("Client with id " + id + " does not exist");
        }
        Client clientToUpdate = clientOptional.get();
        if (client.name() != null && client.name().length() > 0 && !client.name().equals(clientToUpdate.getName())) {
            clientToUpdate.setName(client.name());
        }
        if (client.email() != null && client.email().length() > 0 && !client.email().equals(clientToUpdate.getEmail())) {
            Optional<Client> clientOptionalEmail = clientRepository.findByEmail(client.email());
            if (clientOptionalEmail.isPresent())
                throw new IllegalStateException("email taken");
            clientToUpdate.setEmail(client.email());
        }
        /*if (client.dateOfBirth() != null && !client.dateOfBirth().equals(clientToUpdate.getDateOfBirth())) {
            clientToUpdate.setDateOfBirth(client.dateOfBirth());
        }*/
        clientRepository.save(clientToUpdate);
    }


    @Override
    public void deleteClient(Long clientId) {
        boolean exists = clientRepository.existsById(clientId);
        if (!exists) {
            throw new IllegalStateException("Client with id " + clientId + " does not exist");
        }
        clientRepository.deleteById(clientId);
    }


    @Override
    public void changeClient(Long id, Client client) {
        client.setId(id);
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) {
            throw new IllegalStateException("Client with id " + id + " does not exist");
        }
        clientRepository.save(client);
    }

}





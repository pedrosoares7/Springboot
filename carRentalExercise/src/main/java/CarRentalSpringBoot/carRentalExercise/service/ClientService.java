package CarRentalSpringBoot.carRentalExercise.service;


import CarRentalSpringBoot.carRentalExercise.converter.ClientConverter;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientAlreadyExists;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.repository.ClientRepository;
import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService  implements ClientServiceInterface{

    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
   public List<ClientCreateDto> getClients() {
       List<Client> clients = clientRepository.findAll();
       return clients.stream()
               .map(ClientConverter::fromEntityToDto)
               .toList();
       }
@Override
    public ClientCreateDto getClientCreateDto (Long clientId) throws ClientIdNotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            throw new ClientIdNotFoundException(clientId + Message.CLIENT_ID_NOT_EXISTS);
        }
        Client client = clientOptional.get();
        return ClientConverter.fromEntityToDto(client);
    }
@Override
    public Client getClient (Long clientId) throws ClientIdNotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            throw new ClientIdNotFoundException(clientId + Message.CLIENT_ID_NOT_EXISTS);
        }
        return clientOptional.get();

    }

@Override
    public Client addNewClient(ClientCreateDto client) throws ClientAlreadyExists {
        Optional<Client> clientOptional = this.clientRepository.findByEmail(client.email());
        if (clientOptional.isPresent())
            throw new ClientAlreadyExists(Message.EMAIL_TAKEN);
       @Valid Client newClient = ClientConverter.fromDtoToEntity(client);
       return clientRepository.save(newClient);
    }

@Override
    public void updateClient(Long id, ClientPatchDto client) throws ClientIdNotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            throw new ClientIdNotFoundException(client + Message.CLIENT_ID_NOT_EXISTS);
        }
        Client clientToUpdate = clientOptional.get();
        if (client.name() != null && !client.name().isEmpty() && !client.name().equals(clientToUpdate.getName())) {
            clientToUpdate.setName(client.name());
        }
        if (client.email() != null && !client.email().isEmpty() && !client.email().equals(clientToUpdate.getEmail())) {
          clientToUpdate.setEmail(client.email());
        }
        clientRepository.save(clientToUpdate);
    }

@Override
    public void deleteClient(Long clientId) throws ClientIdNotFoundException {
        boolean exists = clientRepository.existsById(clientId);
        if (!exists) {
            throw new ClientIdNotFoundException(clientId + Message.CLIENT_ID_NOT_EXISTS);
        }
        clientRepository.deleteById(clientId);
    }

@Override
    public void changeClient(Long id, Client client) throws ClientIdNotFoundException {
        client.setId(id);
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            throw new ClientIdNotFoundException(id + Message.CLIENT_ID_NOT_EXISTS);
        }
        clientRepository.save(client);
    }
}





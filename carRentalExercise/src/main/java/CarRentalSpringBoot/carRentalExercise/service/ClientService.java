package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.converter.ClientConverter;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.exceptions.AppExceptions;
import CarRentalSpringBoot.carRentalExercise.repository.ClientRepository;
import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
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

   @Override
   public List<ClientCreateDto> getClients() {
       List<Client> clients = clientRepository.findAll();
       return clients.stream()
               .map(ClientConverter::fromEntityToDto)
               .toList();
       }
    @Override
    public ClientCreateDto getClientCreateDto (Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            throw new AppExceptions(clientId + Message.CLIENT_ID_NOT_EXISTS);
        }
        Client client = clientOptional.get();
        return ClientConverter.fromEntityToDto(client);
    }
    @Override
    public Client getClient (Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            throw new AppExceptions(clientId + Message.CLIENT_ID_NOT_EXISTS);
        }
        return clientOptional.get();

    }


    @Override
    public void addNewClient(ClientCreateDto client) {
        Optional<Client> clientOptional = this.clientRepository.findByEmail(client.email());
        if (clientOptional.isPresent())
            throw new AppExceptions(Message.EMAIL_TAKEN);
        Client newClient = ClientConverter.fromDtoToEntity(client);
        clientRepository.save(newClient);
    }

    @Override
    public void updateClient(Long id, ClientPatchDto client) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) {
            throw new AppExceptions(client + Message.CLIENT_ID_NOT_EXISTS);
        }
        Client clientToUpdate = clientOptional.get();
        if (client.name() != null && client.name().length() > 0 && !client.name().equals(clientToUpdate.getName())) {
            clientToUpdate.setName(client.name());
        }
        if (client.email() != null && client.email().length() > 0 && !client.email().equals(clientToUpdate.getEmail())) {
            Optional<Client> clientOptionalEmail = clientRepository.findByEmail(client.email());
            if (clientOptionalEmail.isPresent())
                throw new AppExceptions(Message.EMAIL_TAKEN);
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
            throw new AppExceptions(clientId + Message.CLIENT_ID_NOT_EXISTS);
        }
        clientRepository.deleteById(clientId);
    }


    @Override
    public void changeClient(Long id, Client client) {
        client.setId(id);
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) {
            throw new AppExceptions(id + Message.CLIENT_ID_NOT_EXISTS);
        }
        clientRepository.save(client);
    }

}





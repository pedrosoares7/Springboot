package CarRentalSpringBoot.carRentalExercise.service;


import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientAlreadyExists;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientIdNotFoundException;
import CarRentalSpringBoot.carRentalExercise.mapper.ClientMapper;
import CarRentalSpringBoot.carRentalExercise.repository.ClientRepository;
import CarRentalSpringBoot.carRentalExercise.utilsmessage.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientServiceInterface {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired ClientMapper clientMapper;
   @Override
   public List<ClientCreateDto> getClients() {
       List<Client> clients = clientRepository.findAll();
       return clients.stream()
               .map(clientMapper::fromEntityToDto)
               .toList();
       }
    @Override
    public ClientCreateDto getClientCreateDto (Long clientId) throws ClientIdNotFoundException {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            throw new ClientIdNotFoundException(clientId + Message.CLIENT_ID_NOT_EXISTS);
        }
        Client client = clientOptional.get();
        return clientMapper.fromEntityToDto(client);
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
    public void addNewClient(ClientCreateDto client) throws ClientAlreadyExists {
        Optional<Client> clientOptional = this.clientRepository.findByEmail(client.email());
        if (clientOptional.isPresent())
            throw new ClientAlreadyExists(Message.EMAIL_TAKEN);
        Client newClient = clientMapper.fromDtoToEntity(client);
        clientRepository.save(newClient);
    }

    @Override
    public void updateClient(Long id, ClientPatchDto client) throws ClientIdNotFoundException, ClientAlreadyExists {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (!clientOptional.isPresent()) {
            throw new ClientIdNotFoundException(client + Message.CLIENT_ID_NOT_EXISTS);
        }
        Client clientToUpdate = clientOptional.get();
        if (client.name() != null && client.name().length() > 0 && !client.name().equals(clientToUpdate.getName())) {
            clientToUpdate.setName(client.name());
        }
        if (client.email() != null && client.email().length() > 0 && !client.email().equals(clientToUpdate.getEmail())) {
            Optional<Client> clientOptionalEmail = clientRepository.findByEmail(client.email());
            if (clientOptionalEmail.isPresent())
                throw new ClientAlreadyExists(Message.EMAIL_TAKEN);
            clientToUpdate.setEmail(client.email());
        }
        /*if (client.dateOfBirth() != null && !client.dateOfBirth().equals(clientToUpdate.getDateOfBirth())) {
            clientToUpdate.setDateOfBirth(client.dateOfBirth());
        }*/
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
        if (!clientOptional.isPresent()) {
            throw new ClientIdNotFoundException(id + Message.CLIENT_ID_NOT_EXISTS);
        }
        clientRepository.save(client);
    }

}





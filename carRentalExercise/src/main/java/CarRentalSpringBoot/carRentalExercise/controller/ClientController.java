package CarRentalSpringBoot.carRentalExercise.controller;


import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientPatchDto;
import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }



    @GetMapping("/")
    public ResponseEntity<List<ClientCreateDto>> getClients(){

        return new ResponseEntity<>(clientService.getClients(),HttpStatus.OK);
    }
    @GetMapping("/{clientId}")//client por ID
    public ResponseEntity<Client> getClient (@PathVariable("clientId") Long clientId) {
        return new ResponseEntity<>(clientService.getClient(clientId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Client> addNewClient(@Valid @RequestBody ClientCreateDto client){
        clientService.addNewClient(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(path = "{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable("clientId") Long id, @Valid @RequestBody ClientPatchDto client) {
        clientService.updateClient(id, client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "{clientId}")
    public ResponseEntity<Client> changeClient(@PathVariable("clientId") Long id, @Valid @RequestBody Client client) {
        clientService.changeClient(id, client);
        client.setId(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping(path = "{clientId}")
    public ResponseEntity<Client> deleteClient(@PathVariable("clientId") Long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

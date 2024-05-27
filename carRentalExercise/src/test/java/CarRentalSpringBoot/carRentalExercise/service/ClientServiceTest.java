package CarRentalSpringBoot.carRentalExercise.service;

import CarRentalSpringBoot.carRentalExercise.converter.ClientConverter;
import CarRentalSpringBoot.carRentalExercise.dto.clientDto.ClientCreateDto;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.exceptions.ClientAlreadyExists;
import CarRentalSpringBoot.carRentalExercise.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
public class ClientServiceTest {

    static MockedStatic<ClientConverter> mockedClientConverter = mockStatic(ClientConverter.class);
    @MockBean
    private ClientRepository clientRepositoryMock;
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        clientService = new ClientService(clientRepositoryMock);

    }

    @Test
    void testCreateClientCallsRepositoryAndConverter() throws ClientAlreadyExists {
        //given
        ClientCreateDto clientCreateDto = new ClientCreateDto("Pedro", "pedro@gmail.com", 123456789, 555555555, LocalDate.of(2000, 1, 1));
        Client client = new Client("Pedro", "pedro@gmail.com", 123456789, 555555555, LocalDate.of(2000, 1, 1));
        when(clientRepositoryMock.findByEmail(clientCreateDto.email())).thenReturn(Optional.empty());
        mockedClientConverter.when(() -> ClientConverter.fromDtoToEntity(clientCreateDto)).thenReturn(client);
        when(clientRepositoryMock.save(Mockito.any())).thenReturn(client);

        //when
        clientService.addNewClient(clientCreateDto);

        //then
        verify(clientRepositoryMock, times(1)).findByEmail(clientCreateDto.email());

        mockedClientConverter.verify(() -> ClientConverter.fromDtoToEntity(clientCreateDto));
        mockedClientConverter.verifyNoMoreInteractions();

        verify(clientRepositoryMock, times(1)).save(client);
        Mockito.verifyNoMoreInteractions(clientRepositoryMock);
        assertEquals(clientCreateDto, clientService.addNewClient(clientCreateDto));

    }

    @Test
    void createClientWithDuplicatedEmailThrowsException() {
        //given
        ClientCreateDto clientCreateDto = new ClientCreateDto("Pedro", "pedro@gmail.com", 123456789, 555555555, LocalDate.of(2000, 1, 1));

        //when
        when(clientRepositoryMock.findByEmail(clientCreateDto.email())).thenReturn(Optional.of(new Client()));
        //then
        assertThrows(ClientAlreadyExists.class, () -> clientService.addNewClient(clientCreateDto));
        assertEquals("Email already taken", assertThrows(ClientAlreadyExists.class, () -> clientService.addNewClient(clientCreateDto)).getMessage());

    }


}

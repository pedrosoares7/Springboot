package CarRentalSpringBoot.carRentalExercise.controller;

import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    private static ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientRepository clientRepository;

    ClientControllerTest() {
    }

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void init() {
        clientRepository.deleteAll();
        clientRepository.resetAutoIncrement();

    }

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Test get all clients when no clients on database returns empty list")
    void testGetAllClientsWhenNoClientsOnDatabaseReturnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Test create a client when client returns status code 201")
    public void testCreateClientReturnCreateAndGetIdEqualsTo1() throws Exception {

        //Given
        String clientJson = "{\"name\": \"João\", \"email\": \"j@eee.com\", \"dateOfBirth\": \"1990-01-01\",\"nif\": \"123456789\", \"driversLicense\": \"365241555\"}";

        //When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isCreated())
                .andReturn();

        //Then
        String responseContent = new String(result.getResponse().getContentAsByteArray());

        Client client = objectMapper.readValue(responseContent, Client.class);

        //assert student id and name using matchers
        assertThat(client.getId()).isEqualTo(1L);
        assertThat(client.getName()).isEqualTo("João");
        assertThat(client.getEmail()).isEqualTo("j@eee.com");
        assertThat(client.getNif()).isEqualTo(123456789);

    }

    @Test
    @DisplayName("Test get all clients when 2 clients on database returns list with 2 clients")
    void testGetAllClientsWhen2ClientsOnDatabaseReturnsListWith2Clients() throws Exception {
        // Create 2 clients (ex.)
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Joao\", \"email\": \"j@eee.com\", \"dateOfBirth\": \"1990-01-01\"," +
                        " \"nif\": \"123456789\", \"driversLicense\": \"365241555\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Maria\", \"email\": \"s@eee.com\", \"dateOfBirth\": \"1992-01-01\"," +
                        " \"nif\": \"789456988\", \"driversLicense\": \"879564555\"}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        //delete created clients (ex.)
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/1"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/2"));
    }

}

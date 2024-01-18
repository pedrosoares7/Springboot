package CarRentalSpringBoot.carRentalExercise.controller;

import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import CarRentalSpringBoot.carRentalExercise.repository.CarRepository;
import CarRentalSpringBoot.carRentalExercise.repository.ClientRepository;
import CarRentalSpringBoot.carRentalExercise.repository.RentalRepository;
import ch.qos.logback.core.net.server.Client;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarRepository carRepository;

    private static ObjectMapper objectMapper;

    RentalControllerTest() {

    }

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    public void init() {
        rentalRepository.deleteAll();
        rentalRepository.resetAutoIncrement();
        carRepository.deleteAll();
        carRepository.resetAutoIncrement();
        clientRepository.deleteAll();
        clientRepository.resetAutoIncrement();
    }

    @Test
    void contextLoads() {

    }


    @Test
    @DisplayName("Test get all rentals when no rentals on database returns empty list")
    void testGetAllRentalsWhenNoRentalsOnDatabaseReturnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rentals/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("Test create a rental when rental returns status code 201")
    public void testCreateRentalReturnCreateAndGetIdEqualsTo1() throws Exception {

        //Given
        String carJson = "{\"brand\": \"BMW\", \"plate\": \"XX-01-XX\", \"horsePower\": \"1500\",\"km\": \"1000\", \"dailyRentalPrice\": \"50\"}";
        String clientJson = "{\"name\": \"João\", \"email\": \"j@eee.com\", \"dateOfBirth\": \"1990-01-01\",\"nif\": \"123456789\", \"driversLicense\": \"365241555\"}";
        String rentalJson = "{\"carId\": \"1\", \"clientId\": \"1\", \"rentalStartDate\": \"2024-01-05\",\"rentalEndDate\": \"2024-01-15\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isCreated())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isCreated())
                .andReturn();

        //When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rentals/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rentalJson))
                .andExpect(status().isCreated())
                .andReturn();

        //Then
        String responseContent = new String(result.getResponse().getContentAsByteArray());

        Rental rental = objectMapper.readValue(responseContent, Rental.class);

        //assert student id and name using matchers

        assertThat(rental.getId()).isEqualTo(1L);
        assertThat(rental.getCar().getId()).isEqualTo(1);
        assertThat(rental.getClient().getId()).isEqualTo(1);
        assertThat(rental.getRentalStartDate()).isEqualTo("2024-01-05");
        assertThat(rental.getRentalEndDate()).isEqualTo("2024-01-15");

    }

    @Test
    @DisplayName("Test get all rentals when 2 rentals on database returns list with 2 rentals")
    void testGetAllRentalsWhen2RentalsOnDatabaseReturnsListWith2Rentals() throws Exception {
        // Create 2 rentals (ex.)

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Joao\", \"email\": \"j@eee.com\", \"dateOfBirth\": \"1990-01-01\"," +
                        " \"nif\": \"123456789\", \"driversLicense\": \"365241555\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Maria\", \"email\": \"s@eee.com\", \"dateOfBirth\": \"1992-01-01\"," +
                        " \"nif\": \"789456988\", \"driversLicense\": \"879564555\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\": \"BMW\", \"plate\": \"XX-01-XX\", \"horsePower\": \"1500\"," +
                        " \"km\": \"1000\", \"dailyRentalPrice\": \"50\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\": \"Mercedes\", \"plate\": \"SS-01-SS\", \"horsePower\": \"1900\"," +
                        " \"km\": \"1500\", \"dailyRentalPrice\": \"80\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rentals/")
                .contentType(MediaType.APPLICATION_JSON)
                .content( "{\"carId\": \"1\", \"clientId\": \"1\", \"rentalStartDate\": \"2024-01-05\",\"rentalEndDate\": \"2024-01-15\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rentals/")
                .contentType(MediaType.APPLICATION_JSON)
                .content( "{\"carId\": \"2\", \"clientId\": \"2\", \"rentalStartDate\": \"2024-01-06\",\"rentalEndDate\": \"2024-01-16\"}"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rentals/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        //delete created rentals (ex.)
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/rentals/*"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/*"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cars/*"));

    }


}


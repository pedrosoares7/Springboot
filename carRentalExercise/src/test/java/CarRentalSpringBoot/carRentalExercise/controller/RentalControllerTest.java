package CarRentalSpringBoot.carRentalExercise.controller;

import CarRentalSpringBoot.carRentalExercise.dto.rentalDto.RentalGetDto;
import CarRentalSpringBoot.carRentalExercise.repository.CarRepository;
import CarRentalSpringBoot.carRentalExercise.repository.ClientRepository;
import CarRentalSpringBoot.carRentalExercise.repository.RentalRepository;
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
class RentalControllerTest {

    private static ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CarRepository carRepository;

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
        String carJson = "{\"brand\": \"BMW\", \"plate\": \"XX-01-XX\", \"horsePower\": \"1500\",\"km\": \"1000\", \"dailyRentalPrice\": \"50\",\"isAvailable\": \"true\"}";
        String clientJson = "{\"name\": \"João\", \"email\": \"j@eee.com\", \"dateOfBirth\": \"1990-01-01\",\"nif\": \"123456789\", \"driversLicense\": \"365241555\"}";
        String rentalJson = "{\"carId\": \"1\", \"clientId\": \"1\", \"rentalStartDate\": \"2024-01-05\",\"rentalEndDate\": \"2024-01-15\",\"isRentalTerminated\": \"false\"}";


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

        RentalGetDto rental = objectMapper.readValue(responseContent, RentalGetDto.class);

        //assert student id and name using matchers

        assertThat(rental.id()).isEqualTo(1L);
        assertThat(rental.car().plate()).isEqualTo("XX-01-XX");
        assertThat(rental.client().nif()).isEqualTo(123456789);
        assertThat(rental.rentalStartDate()).isEqualTo("2024-01-05");
        assertThat(rental.rentalEndDate()).isEqualTo("2024-01-15");

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
                        " \"km\": \"1000\", \"dailyRentalPrice\": \"50\",\"isAvailable\": \"true\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\": \"Mercedes\", \"plate\": \"SS-01-SS\", \"horsePower\": \"1900\"," +
                        " \"km\": \"1500\", \"dailyRentalPrice\": \"80\",\"isAvailable\": \"true\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rentals/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"carId\": \"1\", \"clientId\": \"1\", \"rentalStartDate\": \"2024-01-05\",\"rentalEndDate\": \"2024-01-15\",\"isRentalTerminated\": \"false\"}"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/rentals/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"carId\": \"2\", \"clientId\": \"2\", \"rentalStartDate\": \"2024-01-06\",\"rentalEndDate\": \"2024-01-16\",\"isRentalTerminated\": \"false\"}"));

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


package CarRentalSpringBoot.carRentalExercise.controller;


import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.repository.CarRepository;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
 class CarControllerTest {


        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private CarRepository carRepository;

        private static ObjectMapper objectMapper;

        CarControllerTest(){}

        @BeforeAll
        public static void setUp() {
            objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
        }

        @BeforeEach
        public void init() {
            carRepository.deleteAll();
            carRepository.resetAutoIncrement();

        }
        @Test
        void contextLoads() {
        }

        @Test
        @DisplayName("Test get all cars when no cars on database returns empty list")
        void testGetAllCarsWhenNoCarsOnDatabaseReturnsEmptyList() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars/"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(0)));
        }
        @Test
        @DisplayName("Test create a car when car returns status code 201")
        public void testCreateCarReturnCreateAndGetIdEqualsTo1() throws Exception {

            //Given
            String carJson = "{\"brand\": \"BMW\", \"plate\": \"XX-01-XX\", \"horsePower\": \"1500\",\"km\": \"1000\", \"dailyRentalPrice\": \"50\"}";

            //When
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(carJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            //Then
            String responseContent = new String(result.getResponse().getContentAsByteArray());

            Car car = objectMapper.readValue(responseContent, Car.class);

            //assert student id and name using matchers
            assertThat(car.getId()).isEqualTo(1L);
            assertThat(car.getBrand()).isEqualTo("BMW");
            assertThat(car.getPlate()).isEqualTo("XX-01-XX");
            assertThat(car.getKm()).isEqualTo(1000);
        }
        @Test
        @DisplayName("Test get all cars when 2 cars on database returns list with 2 cars")
        void testGetAllCarsWhen2CarsOnDatabaseReturnsListWith2Cars() throws Exception {
            // Create 2 cars (ex.)
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"brand\": \"BMW\", \"plate\": \"XX-01-XX\", \"horsePower\": \"1500\"," +
                            " \"km\": \"1000\", \"dailyRentalPrice\": \"50\"}"));

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cars/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"brand\": \"Mercedes\", \"plate\": \"SS-01-SS\", \"horsePower\": \"1900\"," +
                            " \"km\": \"1500\", \"dailyRentalPrice\": \"80\"}"));

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cars/"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(2)));

            //delete created clients (ex.)
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cars/1"));
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cars/2"));
        }

    }

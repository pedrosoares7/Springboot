package CarRentalSpringBoot.carRentalExercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Car car;

    private Long rentalPrice;

    private LocalDate rentalStartDate;

    private LocalDate rentalEndDate;

    public Rental(Car newCar, Client newClient) {
    }

    public void setRentalPrice(Long rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}


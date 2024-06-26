package CarRentalSpringBoot.carRentalExercise.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Data

@Builder
@Entity
@Table(name = "Rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Car car;

    private double rentalPrice;

    private LocalDate rentalStartDate;

    private LocalDate rentalEndDate;

    private boolean isRentalTerminated;


    public Rental(Car newCar, Client newClient, LocalDate rentalStartDate, LocalDate rentalEndDate) {
        this.car = newCar;
        this.client = newClient;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        setRentalPrice();
        newCar.setAvailable(false);

    }

    public Rental(Long id, Client client, Car car, double rentalPrice, LocalDate rentalStartDate, LocalDate rentalEndDate, boolean isRentalTerminated) {
        this.id = id;
        this.client = client;
        this.car = car;
        this.rentalPrice = rentalPrice;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.isRentalTerminated = isRentalTerminated;
    }

    public Rental() {
    }

    public void setRentalPrice() {

        long daysBetween = DAYS.between(rentalStartDate, rentalEndDate);
        this.rentalPrice = daysBetween * car.getDailyRentalPrice();

    }


}




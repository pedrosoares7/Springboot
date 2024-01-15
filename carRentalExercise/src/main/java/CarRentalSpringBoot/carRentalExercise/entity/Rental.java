package CarRentalSpringBoot.carRentalExercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Car car;

    private double rentalPrice;

    private LocalDate rentalStartDate;

    private LocalDate rentalEndDate;


    public Rental(Car newCar, Client newClient, LocalDate rentalStartDate, LocalDate rentalEndDate) {
        this.car = newCar;
        this.client = newClient;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        setRentalPrice();
    }

    private void setRentalPrice() {
        //rentalPrice = ((getRentalStartDate().until(getRentalEndDate(), ChronoUnit.DAYS))* car.getDailyRentalPrice());
        long daysBetween = DAYS.between(rentalStartDate, rentalEndDate);
        this.rentalPrice = daysBetween * car.getDailyRentalPrice();

    }


/* public long calculatingRentalPrice(){
        LocalDate startDate = getRentalStartDate();
        LocalDate endDate = getRentalEndDate();
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);

        long rentvalue = (long) (days * car.getDailyRentalPrice());
        this.setRentalPrice(calculatingRentalPrice());
        return rentvalue;
    }*/

 /*   public void setTotalPrice() {
        totalPrice = ((LocalDate.now().until(getReturnDate())).getDays())*car.getDailyRate();
    }*/

  /*  public void setTotalRentalCost(Long totalRentalCost) {
        long daysBetween = DAYS.between(rentalStartDate, rentalEndDate);
        this.totalRentalCost = daysBetween * vehicle.getDailyPrice();*/
    }



package CarRentalSpringBoot.carRentalExercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private List<Rental> rental = new ArrayList<>();

    private String brand;
    @Column(unique = true)
    private String plate;

    private int horsePower;

    private int km;

    private double dailyRentalPrice;


    public Car(Long id, List<Rental> rental, String brand, String plate, int horsePower, int km, double dailyRentalPrice) {
        this.id = id;
        this.rental = rental;
        this.brand = brand;
        this.plate = plate;
        this.horsePower = horsePower;
        this.km = km;
        this.dailyRentalPrice = dailyRentalPrice;
    }

    public Car() {
    }
}

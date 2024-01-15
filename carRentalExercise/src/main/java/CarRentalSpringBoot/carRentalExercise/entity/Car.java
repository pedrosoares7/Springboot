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
@AllArgsConstructor
@NoArgsConstructor
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


}

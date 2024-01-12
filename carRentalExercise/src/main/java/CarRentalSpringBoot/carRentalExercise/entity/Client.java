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
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "client")
    private List<Rental> rental = new ArrayList<>();

    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private int driversLicense;
    @Column(unique = true)
    private int nif;
    private LocalDate dateOfBirth;




    }




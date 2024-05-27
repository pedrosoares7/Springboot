package CarRentalSpringBoot.carRentalExercise.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Rental> rental = new ArrayList<>();


    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private int driversLicense;

    @Column(unique = true)
    private int nif;

    private LocalDate dateOfBirth;

    public Client(String name, String email, int driversLicense, int nif, LocalDate dateOfBirth) {

        this.name = name;
        this.email = email;
        this.driversLicense = driversLicense;
        this.nif = nif;
        this.dateOfBirth = dateOfBirth;
    }

    public Client(Long id, List<Rental> rental, String name, String email, int driversLicense, int nif, LocalDate dateOfBirth) {
        this.id = id;
        this.rental = rental;
        this.name = name;
        this.email = email;
        this.driversLicense = driversLicense;
        this.nif = nif;
        this.dateOfBirth = dateOfBirth;
    }

    public Client() {
    }
}




package CarRentalSpringBoot.carRentalExercise.repository;

import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import CarRentalSpringBoot.carRentalExercise.entity.Rental;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {


    Optional<Rental> findCarById(Car car);
    Optional<Rental> findClientById(Client client);


    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE rentals AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}

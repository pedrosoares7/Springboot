package CarRentalSpringBoot.carRentalExercise.repository;

import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository <Car, Long> {

    Optional<Car> findByPlate(String plate);


    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE cars AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}

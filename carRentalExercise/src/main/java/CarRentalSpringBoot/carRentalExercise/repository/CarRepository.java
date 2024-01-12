package CarRentalSpringBoot.carRentalExercise.repository;

import CarRentalSpringBoot.carRentalExercise.entity.Car;
import CarRentalSpringBoot.carRentalExercise.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository <Car, Long> {

    Optional<Car> findByPlate(String plate);
}

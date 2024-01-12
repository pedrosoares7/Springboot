package CarRentalSpringBoot.carRentalExercise.repository;

import CarRentalSpringBoot.carRentalExercise.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {


Optional<Client> findByEmail(String email);


}

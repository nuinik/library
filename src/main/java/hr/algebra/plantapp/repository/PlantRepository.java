package hr.algebra.plantapp.repository;
import hr.algebra.plantapp.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PlantRepository extends JpaRepository<Plant, Long> {}

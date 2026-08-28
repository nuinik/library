package hr.algebra.plantapp.repository;
import hr.algebra.plantapp.entity.PlantType;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PlantTypeRepository extends JpaRepository<PlantType, Long> {}

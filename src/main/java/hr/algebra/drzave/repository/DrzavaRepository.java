package hr.ispit.drzave.repository;
import hr.ispit.drzave.entity.Drzava; import org.springframework.data.jpa.repository.JpaRepository;
public interface DrzavaRepository extends JpaRepository<Drzava,Long> { boolean existsByNazivIgnoreCase(String naziv); }


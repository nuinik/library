package hr.ispit.biljke.repository;
import hr.ispit.biljke.entity.Biljka; import org.springframework.data.jpa.repository.JpaRepository;
public interface BiljkaRepository extends JpaRepository<Biljka,Long> { boolean existsByNazivIgnoreCase(String naziv); }


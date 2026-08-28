package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.Upis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpisRepositorySpringData extends JpaRepository<Upis, Integer> {
}

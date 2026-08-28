package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.Polaznik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolaznikRepositorySpringData extends JpaRepository<Polaznik, Integer> {
}

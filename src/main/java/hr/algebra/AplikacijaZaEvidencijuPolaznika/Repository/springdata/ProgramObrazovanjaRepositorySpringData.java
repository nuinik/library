package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.ProgramObrazovanja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramObrazovanjaRepositorySpringData extends JpaRepository <ProgramObrazovanja, Integer> {
}

package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.jdbctemplate;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.ProgramObrazovanja;

import java.util.List;
import java.util.Optional;

public interface ProgramObrazovanjaRepository {
    List<ProgramObrazovanja> findAll();
    Optional<ProgramObrazovanja> findById(int id);
    int save(ProgramObrazovanja programObrazovanja);
    int update(ProgramObrazovanja programObrazovanja);
    int deleteById(int id);
}

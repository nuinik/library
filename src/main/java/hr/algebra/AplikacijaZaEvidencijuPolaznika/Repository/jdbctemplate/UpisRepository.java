package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.jdbctemplate;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.Upis;

import java.util.List;
import java.util.Optional;

public interface UpisRepository {
    List<Upis> findAll();
    Optional<Upis> findById(int id);
    int save(Upis upis);
    int update(Upis upis);
    int deleteById(int id);
}

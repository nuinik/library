package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.jdbctemplate;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.Polaznik;

import java.util.List;
import java.util.Optional;

public interface PolaznikRepository {
    List<Polaznik> findAll();
    Optional<Polaznik> findById(int id);
    int save(Polaznik polaznik);
    int update(Polaznik polaznik);
    int deleteById(int id);
}

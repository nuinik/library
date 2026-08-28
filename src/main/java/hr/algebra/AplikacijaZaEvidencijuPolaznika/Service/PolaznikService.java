package hr.algebra.AplikacijaZaEvidencijuPolaznika.Service;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.dto.PolaznikDTO;

import java.util.List;
import java.util.Optional;

public interface PolaznikService {
    List<PolaznikDTO> getAllPolaznik();
    Optional<PolaznikDTO> getPolaznikById(int id);
    PolaznikDTO savePolaznik(PolaznikDTO polaznikDTO);
    void deletePolaznik(int id);
    Optional<PolaznikDTO> updatePolaznik(PolaznikDTO polaznikDTO, int id);
}

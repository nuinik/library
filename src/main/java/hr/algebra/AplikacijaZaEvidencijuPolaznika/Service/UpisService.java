package hr.algebra.AplikacijaZaEvidencijuPolaznika.Service;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.dto.UpisDTO;

import java.util.List;
import java.util.Optional;

public interface UpisService {
    List<UpisDTO> getAllUpis();
    Optional<UpisDTO> getUpisById(int id);
    UpisDTO saveUpis(UpisDTO upisDTO);
    void deleteUpis(int id);
    Optional<UpisDTO> updateUpis(UpisDTO upisDTO, int id);
}

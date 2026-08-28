package hr.algebra.AplikacijaZaEvidencijuPolaznika.Service;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.dto.ProgramObrazovanjaDTO;

import java.util.List;
import java.util.Optional;

public interface ProgramObrazovanjaService {
    List<ProgramObrazovanjaDTO> getAllPrograms();
    Optional<ProgramObrazovanjaDTO> getProgramById(int id);
    ProgramObrazovanjaDTO saveProgram(ProgramObrazovanjaDTO programObrazovanja);
    void deleteProgram(int id);
    Optional<ProgramObrazovanjaDTO> updateProgram(ProgramObrazovanjaDTO programObrazovanjaDTO, int id);
}

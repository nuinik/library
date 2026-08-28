package hr.algebra.AplikacijaZaEvidencijuPolaznika.Service;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.ProgramObrazovanja;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata.ProgramObrazovanjaRepositorySpringData;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.dto.ProgramObrazovanjaDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProgramObrazovanjaServiceImpl implements ProgramObrazovanjaService {

    private ProgramObrazovanjaRepositorySpringData programObrazovanjaRepositorySpringData;

    public List<ProgramObrazovanjaDTO> getAllPrograms() {
        return programObrazovanjaRepositorySpringData.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProgramObrazovanjaDTO> getProgramById(int id) {
        return programObrazovanjaRepositorySpringData.findById(id)
                .map(this::convertToDTO);
    }

    public ProgramObrazovanjaDTO saveProgram(ProgramObrazovanjaDTO programDTO) {
        ProgramObrazovanja program = convertToEntity(programDTO);
        ProgramObrazovanja savedProgram = programObrazovanjaRepositorySpringData.save(program);
        return convertToDTO(savedProgram);
    }

    public void deleteProgram(int id) {
        programObrazovanjaRepositorySpringData.deleteById(id);
    }

    @Override
    public Optional<ProgramObrazovanjaDTO> updateProgram(ProgramObrazovanjaDTO programObrazovanjaDTO, int id) {
        ProgramObrazovanja programObrazovanja = convertToEntity(programObrazovanjaDTO);
        Optional<ProgramObrazovanja> programObrazovanjaUpdateOptional = programObrazovanjaRepositorySpringData.findById(id);
        if(programObrazovanjaUpdateOptional.isPresent()) {
            ProgramObrazovanja programObrazovanjaToUpdate = programObrazovanjaUpdateOptional.get();
            programObrazovanjaToUpdate.setNaziv(programObrazovanjaDTO.getNaziv());
            programObrazovanjaToUpdate.setCsvet(programObrazovanjaDTO.getCsvet());
            ProgramObrazovanja updatedProgramObrazovanja = programObrazovanjaRepositorySpringData.save(programObrazovanjaToUpdate);
            return Optional.of(convertToDTO(updatedProgramObrazovanja));
        }
        return Optional.empty();
    }

    private ProgramObrazovanjaDTO convertToDTO(ProgramObrazovanja program) {
        ProgramObrazovanjaDTO programDTO = new ProgramObrazovanjaDTO();
        programDTO.setProgramObrazovanjaId(program.getProgramObrazovanjaId());
        programDTO.setNaziv(program.getNaziv());
        programDTO.setCsvet(program.getCsvet());
        return programDTO;
    }

    private ProgramObrazovanja convertToEntity(ProgramObrazovanjaDTO programDTO) {
        ProgramObrazovanja program = new ProgramObrazovanja();
        program.setProgramObrazovanjaId(programDTO.getProgramObrazovanjaId());
        program.setNaziv(programDTO.getNaziv());
        program.setCsvet(programDTO.getCsvet());
        return program;
    }
}

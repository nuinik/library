package hr.algebra.AplikacijaZaEvidencijuPolaznika.Service;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.Polaznik;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.ProgramObrazovanja;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.Upis;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata.PolaznikRepositorySpringData;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata.ProgramObrazovanjaRepositorySpringData;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata.UpisRepositorySpringData;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.dto.UpisDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UpisServiceImpl implements UpisService {

    private UpisRepositorySpringData upisRepositorySpringData;
    private ProgramObrazovanjaRepositorySpringData programObrazovanjaRepositorySpringData;
    private PolaznikRepositorySpringData polaznikRepositorySpringData;

    public List<UpisDTO> getAllUpis() {
        return upisRepositorySpringData.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UpisDTO> getUpisById(int id) {
        return upisRepositorySpringData.findById(id)
                .map(this::convertToDTO);
    }

    public UpisDTO saveUpis(UpisDTO upisDTO) {
        Upis upis = convertToEntity(upisDTO);
        Upis savedUpis = upisRepositorySpringData.save(upis);
        return convertToDTO(savedUpis);
    }

    public void deleteUpis(int id) {
        upisRepositorySpringData.deleteById(id);
    }

    @Override
    public Optional<UpisDTO> updateUpis(UpisDTO upisDTO, int id) {
        Upis upis = convertToEntity(upisDTO);
        Optional<Upis> upisUpdateOptional =
                upisRepositorySpringData.findById(id);
        if(upisUpdateOptional.isPresent()) {
            Upis upisToUpdate = upisUpdateOptional.get();
            Optional<Polaznik> polaznikOptional = polaznikRepositorySpringData.findById(upisDTO.getIdPolaznik());
            if(polaznikOptional.isPresent()) {
                upisToUpdate.getPolaznik().setIme(polaznikOptional.get().getIme());
                upisToUpdate.getPolaznik().setPrezime(polaznikOptional.get().getPrezime());
            }

            Optional<ProgramObrazovanja> programObrazovanjaToUpdate =
                    programObrazovanjaRepositorySpringData.findById(upisDTO.getIdProgramObrazovanja());

            if(programObrazovanjaToUpdate.isPresent()) {
                upisToUpdate.getProgramObrazovanja().setCsvet(programObrazovanjaToUpdate.get().getCsvet());
                upisToUpdate.getProgramObrazovanja().setNaziv(programObrazovanjaToUpdate.get().getNaziv());
            }

            Upis savedUpis = upisRepositorySpringData.save(upisToUpdate);
            return Optional.of(convertToDTO(savedUpis));
        }
        return Optional.empty();
    }

    private UpisDTO convertToDTO(Upis upis) {
        UpisDTO upisDTO = new UpisDTO();
        upisDTO.setUpisId(upis.getUpisId());
        upisDTO.setIdProgramObrazovanja(upis.getProgramObrazovanja().getProgramObrazovanjaId());
        upisDTO.setIdPolaznik(upis.getPolaznik().getPolaznikId());
        return upisDTO;
    }

    private Upis convertToEntity(UpisDTO upisDTO) {
        Upis upis = new Upis();
        upis.setUpisId(upisDTO.getUpisId());

        ProgramObrazovanja program = programObrazovanjaRepositorySpringData.findById(upisDTO.getIdProgramObrazovanja())
                .orElseThrow(() -> new RuntimeException("Program nije pronadjen"));
        upis.setProgramObrazovanja(program);

        Polaznik polaznik = polaznikRepositorySpringData.findById(upisDTO.getIdPolaznik())
                .orElseThrow(() -> new RuntimeException("Polaznik nije pronadjen"));
        upis.setPolaznik(polaznik);

        return upis;
    }
}

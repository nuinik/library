package hr.algebra.AplikacijaZaEvidencijuPolaznika.Service;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.Polaznik;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata.PolaznikRepositorySpringData;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.dto.PolaznikDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PolaznikServiceImpl implements PolaznikService {

    private PolaznikRepositorySpringData polaznikRepositorySpringData;

    public List<PolaznikDTO> getAllPolaznik() {
        return polaznikRepositorySpringData.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PolaznikDTO> getPolaznikById(int id) {
        return polaznikRepositorySpringData.findById(id)
                .map(this::convertToDTO);
    }

    public PolaznikDTO savePolaznik(PolaznikDTO polaznikDTO) {
        Polaznik polaznik = convertToEntity(polaznikDTO);
        Polaznik savedPolaznik = polaznikRepositorySpringData.save(polaznik);
        return convertToDTO(savedPolaznik);
    }

    public void deletePolaznik(int id) {
        polaznikRepositorySpringData.deleteById(id);
    }

    @Override
    public Optional<PolaznikDTO> updatePolaznik(PolaznikDTO polaznikDTO, int id) {
        Polaznik polaznik = convertToEntity(polaznikDTO);
        Optional <Polaznik> polaznikToUpdateOptional = polaznikRepositorySpringData.findById(id);
        if(polaznikToUpdateOptional.isPresent()) {
            Polaznik polaznikToUpdate = polaznikToUpdateOptional.get();
            polaznikToUpdate.setIme(polaznik.getIme());
            polaznikToUpdate.setPrezime(polaznik.getPrezime());
            Polaznik updatedPolaznik = polaznikRepositorySpringData.save(polaznikToUpdate);
            return Optional.of(convertToDTO(updatedPolaznik));
        }
        return Optional.empty();
    }

    private PolaznikDTO convertToDTO(Polaznik polaznik) {
        PolaznikDTO polaznikDTO = new PolaznikDTO();
        polaznikDTO.setPolaznikID(polaznik.getPolaznikId());
        polaznikDTO.setIme(polaznik.getIme());
        polaznikDTO.setPrezime(polaznik.getPrezime());
        return polaznikDTO;
    }

    private Polaznik convertToEntity(PolaznikDTO polaznikDTO) {
        Polaznik polaznik = new Polaznik();
        polaznik.setPolaznikId(polaznik.getPolaznikId());
        polaznik.setIme(polaznikDTO.getIme());
        polaznik.setPrezime(polaznik.getPrezime());
        polaznik.setPolaznikId(polaznik.getPolaznikId());
        return polaznik;
    }
}

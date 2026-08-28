import hr.ispit.drzave.dto.DrzavaDTO;
import hr.ispit.drzave.entity.Drzava;
import hr.ispit.drzave.entity.Kontinent;
import hr.ispit.drzave.exception.NotFoundException;
import hr.ispit.drzave.repository.DrzavaRepository;
import hr.ispit.drzave.repository.KontinentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DrzavaService {

    private final DrzavaRepository drzavaRepository;
    private final KontinentRepository kontinentRepository;

    public DrzavaService(
            DrzavaRepository drzavaRepository,
            KontinentRepository kontinentRepository
    ) {
        this.drzavaRepository = drzavaRepository;
        this.kontinentRepository = kontinentRepository;
    }

    @Transactional(readOnly = true)
    public List<DrzavaDTO> findAll() {
        return drzavaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DrzavaDTO findById(Long id) {
        Drzava drzava = findDrzavaById(id);

        return mapToDTO(drzava);
    }

    public DrzavaDTO create(DrzavaDTO drzavaDTO) {
        if (drzavaRepository.existsByNazivIgnoreCase(
                drzavaDTO.getNaziv()
        )) {
            throw new IllegalArgumentException(
                    "Država s tim nazivom već postoji"
            );
        }

        Drzava drzava = new Drzava();

        applyDTOToEntity(drzava, drzavaDTO);

        Drzava spremljenaDrzava =
                drzavaRepository.save(drzava);

        return mapToDTO(spremljenaDrzava);
    }

    public DrzavaDTO update(
            Long id,
            DrzavaDTO drzavaDTO
    ) {
        Drzava drzava = findDrzavaById(id);

        applyDTOToEntity(drzava, drzavaDTO);

        Drzava azuriranaDrzava =
                drzavaRepository.save(drzava);

        return mapToDTO(azuriranaDrzava);
    }

    public void delete(Long id) {
        Drzava drzava = findDrzavaById(id);

        drzavaRepository.delete(drzava);
    }

    private Drzava findDrzavaById(Long id) {
        return drzavaRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Država nije pronađena: " + id
                        )
                );
    }

    private Kontinent findKontinentById(Long id) {
        return kontinentRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Kontinent nije pronađen: " + id
                        )
                );
    }

    private void applyDTOToEntity(
            Drzava drzava,
            DrzavaDTO drzavaDTO
    ) {
        Kontinent kontinent = findKontinentById(
                drzavaDTO.getContinentID()
        );

        drzava.setNaziv(
                drzavaDTO.getNaziv().trim()
        );

        drzava.setBrojStanovnika(
                drzavaDTO.getBrojStanovnika()
        );

        drzava.setKontinent(kontinent);
    }

    private DrzavaDTO mapToDTO(Drzava drzava) {
        DrzavaDTO drzavaDTO = new DrzavaDTO();

        drzavaDTO.setDrzavaID(
                drzava.getId()
        );

        drzavaDTO.setNaziv(
                drzava.getNaziv()
        );

        drzavaDTO.setBrojStanovnika(
                drzava.getBrojStanovnika()
        );

        drzavaDTO.setContinentID(
                drzava.getKontinent().getId()
        );

        drzavaDTO.setContinentNaziv(
                drzava.getKontinent().getNaziv()
        );

        return drzavaDTO;
    }
}

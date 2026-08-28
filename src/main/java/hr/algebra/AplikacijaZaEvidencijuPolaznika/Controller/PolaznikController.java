package hr.algebra.AplikacijaZaEvidencijuPolaznika.Controller;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Service.PolaznikService;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.dto.PolaznikDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/polaznik")
public class PolaznikController {

    private PolaznikService polaznikService;

    @GetMapping
    public List<PolaznikDTO> getAllPolaznik() {
        return polaznikService.getAllPolaznik();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolaznikDTO> getPolaznikById(@PathVariable int id) {
        Optional<PolaznikDTO> polaznikDTO = polaznikService.getPolaznikById(id);
        return polaznikDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PolaznikDTO> createPolaznik(@Valid @RequestBody PolaznikDTO polaznikDTO) {
        PolaznikDTO savedPolaznikDTO = polaznikService.savePolaznik(polaznikDTO);
        return new ResponseEntity<>(savedPolaznikDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PolaznikDTO> updatePolaznik(@PathVariable int id, @Valid @RequestBody PolaznikDTO polaznikDTO) {
        if (!polaznikService.getPolaznikById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        polaznikDTO.setPolaznikID(id);
        return ResponseEntity.ok(polaznikService.updatePolaznik(polaznikDTO, id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolaznik(@PathVariable int id) {
        if (!polaznikService.getPolaznikById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        polaznikService.deletePolaznik(id);
        return ResponseEntity.noContent().build();
    }
}

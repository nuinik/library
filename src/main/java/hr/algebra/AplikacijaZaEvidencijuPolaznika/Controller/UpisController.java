package hr.algebra.AplikacijaZaEvidencijuPolaznika.Controller;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Service.UpisService;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.dto.UpisDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/upis")
@AllArgsConstructor
public class UpisController {

    private UpisService upisService;

    @GetMapping
    public List<UpisDTO> getAllUpis() {
        return upisService.getAllUpis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpisDTO> getUpisById(@PathVariable int id) {
        Optional<UpisDTO> upisDTO = upisService.getUpisById(id);
        return upisDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UpisDTO> createUpis(@Valid @RequestBody UpisDTO upisDTO) {
        UpisDTO savedUpisDTO = upisService.saveUpis(upisDTO);
        return new ResponseEntity<>(savedUpisDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpisDTO> updateUpis(@PathVariable int id, @Valid @RequestBody UpisDTO upisDTO) {
        if (!upisService.getUpisById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        upisDTO.setUpisId(id);
        return ResponseEntity.ok(upisService.updateUpis(upisDTO, id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUpis(@PathVariable int id) {
        if (!upisService.getUpisById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        upisService.deleteUpis(id);
        return ResponseEntity.noContent().build();
    }
}

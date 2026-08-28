package hr.algebra.AplikacijaZaEvidencijuPolaznika.Controller;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Service.ProgramObrazovanjaService;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.dto.ProgramObrazovanjaDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/program")
@AllArgsConstructor
public class ProgramObrazovanjaController {

    private ProgramObrazovanjaService programObrazovanjaService;

    @GetMapping("/{id}")
    public ResponseEntity<ProgramObrazovanjaDTO> getProgramById(@PathVariable int id) {
        Optional<ProgramObrazovanjaDTO> programDTO = programObrazovanjaService.getProgramById(id);
        return programDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProgramObrazovanjaDTO> createProgram(@Valid @RequestBody ProgramObrazovanjaDTO programDTO) {
        ProgramObrazovanjaDTO savedProgramObrazovanjaDTO = programObrazovanjaService.saveProgram(programDTO);
        return new ResponseEntity<>(savedProgramObrazovanjaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramObrazovanjaDTO> updateProgram(@PathVariable int id, @Valid @RequestBody ProgramObrazovanjaDTO programDTO) {
        if(!programObrazovanjaService.getProgramById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        programDTO.setProgramObrazovanjaId(id);
        return ResponseEntity.ok(programObrazovanjaService.updateProgram(programDTO, id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable int id) {
        if (programObrazovanjaService.getProgramById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        programObrazovanjaService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}

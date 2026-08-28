package hr.ispit.drzave.controller;

import hr.ispit.drzave.dto.DrzavaDTO;
import hr.ispit.drzave.service.DrzavaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/drzave")
public class DrzavaController {

    private final DrzavaService drzavaService;

    public DrzavaController(DrzavaService drzavaService) {
        this.drzavaService = drzavaService;
    }

    @GetMapping
    public ResponseEntity<List<DrzavaDTO>> findAll() {
        return ResponseEntity.ok(drzavaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrzavaDTO> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(drzavaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DrzavaDTO> create(
            @Valid @RequestBody DrzavaDTO drzavaDTO
    ) {
        DrzavaDTO kreiranaDrzava =
                drzavaService.create(drzavaDTO);

        URI lokacija = URI.create(
                "/api/drzave/" + kreiranaDrzava.getDrzavaID()
        );

        return ResponseEntity
                .created(lokacija)
                .body(kreiranaDrzava);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrzavaDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody DrzavaDTO drzavaDTO
    ) {
        DrzavaDTO azuriranaDrzava =
                drzavaService.update(id, drzavaDTO);

        return ResponseEntity.ok(azuriranaDrzava);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        drzavaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

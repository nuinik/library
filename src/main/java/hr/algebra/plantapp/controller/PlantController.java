package hr.algebra.plantapp.controller;

import hr.algebra.plantapp.dto.PlantRequest;
import hr.algebra.plantapp.dto.PlantResponse;
import hr.algebra.plantapp.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {
    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public ResponseEntity<List<PlantResponse>> findAll() {
        return ResponseEntity.ok(plantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(plantService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PlantResponse> create(@Valid @RequestBody PlantRequest request) {
        PlantResponse createdPlant = plantService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody PlantRequest request) {
        return ResponseEntity.ok(plantService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

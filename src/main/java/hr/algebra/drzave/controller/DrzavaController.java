package hr.ispit.drzave.controller;
import hr.ispit.drzave.dto.*; import hr.ispit.drzave.service.DrzavaService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.net.URI; import java.util.*;
@RestController @RequestMapping("/api/drzave") public class DrzavaController {
 private final DrzavaService service; public DrzavaController(DrzavaService s){service=s;}
 @GetMapping public ResponseEntity<List<DrzavaDto>> all(){return ResponseEntity.ok(service.findAll());}
 @GetMapping("/{id}") public ResponseEntity<DrzavaDto> one(@PathVariable Long id){return ResponseEntity.ok(service.findById(id));}
 @PostMapping public ResponseEntity<DrzavaDto> create(@Valid @RequestBody DrzavaRequest r){DrzavaDto d=service.create(r);return ResponseEntity.created(URI.create("/api/drzave/"+d.id())).body(d);}
 @PutMapping("/{id}") public ResponseEntity<DrzavaDto> update(@PathVariable Long id,@Valid @RequestBody DrzavaRequest r){return ResponseEntity.ok(service.update(id,r));}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}


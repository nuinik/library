package hr.ispit.biljke.controller;
import hr.ispit.biljke.dto.*; import hr.ispit.biljke.service.BiljkaService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.net.URI; import java.util.*;
@RestController @RequestMapping("/api/biljke") public class BiljkaController {
 private final BiljkaService service; public BiljkaController(BiljkaService s){service=s;}
 @GetMapping public ResponseEntity<List<BiljkaDto>> all(){return ResponseEntity.ok(service.findAll());}
 @GetMapping("/{id}") public ResponseEntity<BiljkaDto> one(@PathVariable Long id){return ResponseEntity.ok(service.findById(id));}
 @PostMapping public ResponseEntity<BiljkaDto> create(@Valid @RequestBody BiljkaRequest r){BiljkaDto d=service.create(r);return ResponseEntity.created(URI.create("/api/biljke/"+d.id())).body(d);}
 @PutMapping("/{id}") public ResponseEntity<BiljkaDto> update(@PathVariable Long id,@Valid @RequestBody BiljkaRequest r){return ResponseEntity.ok(service.update(id,r));}
 @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}


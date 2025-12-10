package com.concessionario.controller;

import com.concessionario.model.CasaAutomobilistica;
import com.concessionario.repository.CasaAutomobilisticaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/case-automobilistiche")
@CrossOrigin(origins = "http://localhost:4200")
public class CasaAutomobilisticaController {
    
    @Autowired
    private CasaAutomobilisticaRepository casaRepository;
    
    // GET /api/case-automobilistiche - Tutte le case automobilistiche
    @GetMapping
    public ResponseEntity<List<CasaAutomobilistica>> getAllCaseAutomobilistiche() {
        List<CasaAutomobilistica> caseAutomobilistiche = casaRepository.findAll();
        return ResponseEntity.ok(caseAutomobilistiche);
    }
    
    // GET /api/case-automobilistiche/{id} - Casa automobilistica specifica
    @GetMapping("/{id}")
    public ResponseEntity<CasaAutomobilistica> getCasaAutomobilisticaById(@PathVariable Integer id) {
        Optional<CasaAutomobilistica> casa = casaRepository.findById(id);
        return casa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // POST /api/case-automobilistiche - Nuova casa automobilistica
    @PostMapping
    public ResponseEntity<CasaAutomobilistica> createCasaAutomobilistica(@Valid @RequestBody CasaAutomobilistica casa) {
        CasaAutomobilistica savedCasa = casaRepository.save(casa);
        return new ResponseEntity<>(savedCasa, HttpStatus.CREATED);
    }
    
    // PUT /api/case-automobilistiche/{id} - Aggiorna casa automobilistica
    @PutMapping("/{id}")
    public ResponseEntity<CasaAutomobilistica> updateCasaAutomobilistica(@PathVariable Integer id, @Valid @RequestBody CasaAutomobilistica casa) {
        if (!casaRepository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        casa.setId(id);
        CasaAutomobilistica updatedCasa = casaRepository.save(casa);
        return ResponseEntity.ok(updatedCasa);
    }
    
    // DELETE /api/case-automobilistiche/{id} - Elimina casa automobilistica
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCasaAutomobilistica(@PathVariable Integer id) {
        if (!casaRepository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        casaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    // GET /api/case-automobilistiche/search?nazione=... - Cerca per nazione
    @GetMapping("/search")
    public ResponseEntity<List<CasaAutomobilistica>> searchByNazione(@RequestParam String nazione) {
        List<CasaAutomobilistica> caseAutomobilistiche = casaRepository.findByNazioneContainingIgnoreCase(nazione);
        return ResponseEntity.ok(caseAutomobilistiche);
    }
}

package com.concessionario.controller;

import com.concessionario.model.Veicolo;
import com.concessionario.service.VeicoloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/veicoli")
@CrossOrigin(origins = "http://localhost:4200")
public class VeicoloController {
    
    @Autowired
    private VeicoloService veicoloService;
    
    // GET /api/veicoli - Tutti i veicoli con paginazione
    @GetMapping
    public ResponseEntity<Page<Veicolo>> getAllVeicoli(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        
        Page<Veicolo> veicoli = veicoloService.findAll(pageable);
        return ResponseEntity.ok(veicoli);
    }
    
    // GET /api/veicoli/{id} - Veicolo specifico
    @GetMapping("/{id}")
    public ResponseEntity<Veicolo> getVeicoloById(@PathVariable Integer id) {
        Optional<Veicolo> veicolo = veicoloService.findById(id);
        return veicolo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // POST /api/veicoli - Nuovo veicolo
    @PostMapping
    public ResponseEntity<Veicolo> createVeicolo(@Valid @RequestBody Veicolo veicolo) {
        Veicolo savedVeicolo = veicoloService.save(veicolo);
        return new ResponseEntity<>(savedVeicolo, HttpStatus.CREATED);
    }
    
    // PUT /api/veicoli/{id} - Aggiorna veicolo
    @PutMapping("/{id}")
    public ResponseEntity<Veicolo> updateVeicolo(@PathVariable Integer id, @Valid @RequestBody Veicolo veicolo) {
        if (!veicoloService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        veicolo.setId(id);
        Veicolo updatedVeicolo = veicoloService.save(veicolo);
        return ResponseEntity.ok(updatedVeicolo);
    }
    
    // DELETE /api/veicoli/{id} - Elimina veicolo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeicolo(@PathVariable Integer id) {
        if (!veicoloService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        veicoloService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    // GET /api/veicoli/search?marca=... - Cerca per marca
    @GetMapping("/search")
    public ResponseEntity<List<Veicolo>> searchVeicoli(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Integer categoriaId,
            @RequestParam(required = false) Integer casaAutomobilisticaId,
            @RequestParam(required = false) BigDecimal prezzoMassimo,
            @RequestParam(required = false, defaultValue = "false") boolean soloDisponibili) {
        
        List<Veicolo> veicoli;
        
        if (marca != null && prezzoMassimo != null) {
            veicoli = veicoloService.findByMarcaAndPrezzoMassimo(marca, prezzoMassimo);
        } else if (marca != null) {
            veicoli = veicoloService.findByMarca(marca);
        } else if (categoriaId != null) {
            veicoli = veicoloService.findByCategoria(categoriaId);
        } else if (casaAutomobilisticaId != null) {
            veicoli = veicoloService.findByCasaAutomobilistica(casaAutomobilisticaId);
        } else if (prezzoMassimo != null) {
            veicoli = veicoloService.findByPrezzoMassimo(prezzoMassimo);
        } else if (soloDisponibili) {
            veicoli = veicoloService.findDisponibili();
        } else {
            veicoli = veicoloService.findAll();
        }
        
        return ResponseEntity.ok(veicoli);
    }
    
    // GET /api/veicoli/statistiche - Statistiche sui veicoli
    @GetMapping("/statistiche")
    public ResponseEntity<Map<String, Object>> getStatistiche() {
        Map<String, Object> statistiche = new HashMap<>();
        
        statistiche.put("prezzoMedio", veicoloService.calcolaPrezzoMedio());
        statistiche.put("veicoloPiuEconomico", veicoloService.findVeicoloPiuEconomico());
        statistiche.put("prezzoMedioPerMarca", veicoloService.calcolaPrezzoMedioPerMarca());
        statistiche.put("conteggioPerCategoria", veicoloService.contaVeicoliPerCategoria());
        statistiche.put("conteggioPerCasaAutomobilistica", veicoloService.contaVeicoliPerCasaAutomobilistica());
        
        return ResponseEntity.ok(statistiche);
    }
}

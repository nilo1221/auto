package com.concessionario.controller;

import com.concessionario.model.CategoriaVeicolo;
import com.concessionario.repository.CategoriaVeicoloRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorie-veicolo")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaVeicoloController {
    
    @Autowired
    private CategoriaVeicoloRepository categoriaRepository;
    
    // GET /api/categorie-veicolo - Tutte le categorie
    @GetMapping
    public ResponseEntity<List<CategoriaVeicolo>> getAllCategorie() {
        List<CategoriaVeicolo> categorie = categoriaRepository.findAll();
        return ResponseEntity.ok(categorie);
    }
    
    // GET /api/categorie-veicolo/{id} - Categoria specifica
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaVeicolo> getCategoriaById(@PathVariable Integer id) {
        Optional<CategoriaVeicolo> categoria = categoriaRepository.findById(id);
        return categoria.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // POST /api/categorie-veicolo - Nuova categoria
    @PostMapping
    public ResponseEntity<CategoriaVeicolo> createCategoria(@Valid @RequestBody CategoriaVeicolo categoria) {
        CategoriaVeicolo savedCategoria = categoriaRepository.save(categoria);
        return new ResponseEntity<>(savedCategoria, HttpStatus.CREATED);
    }
    
    // PUT /api/categorie-veicolo/{id} - Aggiorna categoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaVeicolo> updateCategoria(@PathVariable Integer id, @Valid @RequestBody CategoriaVeicolo categoria) {
        if (!categoriaRepository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        categoria.setId(id);
        CategoriaVeicolo updatedCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.ok(updatedCategoria);
    }
    
    // DELETE /api/categorie-veicolo/{id} - Elimina categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        if (!categoriaRepository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

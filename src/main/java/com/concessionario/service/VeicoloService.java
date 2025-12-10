package com.concessionario.service;

import com.concessionario.model.Veicolo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VeicoloService {
    
    // Operazioni CRUD
    Veicolo save(Veicolo veicolo);
    Optional<Veicolo> findById(Integer id);
    List<Veicolo> findAll();
    Page<Veicolo> findAll(Pageable pageable);
    void deleteById(Integer id);
    
    // Ricerche
    List<Veicolo> findByMarca(String marca);
    List<Veicolo> findByCategoria(Integer categoriaId);
    List<Veicolo> findByCasaAutomobilistica(Integer casaAutomobilisticaId);
    List<Veicolo> findByPrezzoMassimo(BigDecimal prezzoMassimo);
    List<Veicolo> findDisponibili();
    
    // Statistiche
    BigDecimal calcolaPrezzoMedio();
    Veicolo findVeicoloPiuEconomico();
    Map<String, BigDecimal> calcolaPrezzoMedioPerMarca();
    Map<String, Long> contaVeicoliPerCategoria();
    Map<String, Long> contaVeicoliPerCasaAutomobilistica();
    
    // Query complesse
    List<Veicolo> findByMarcaAndPrezzoMassimo(String marca, BigDecimal prezzoMassimo);
}

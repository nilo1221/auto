package com.concessionario.repository;

import com.concessionario.model.CasaAutomobilistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CasaAutomobilisticaRepository extends JpaRepository<CasaAutomobilistica, Integer> {
    
    // Trova casa automobilistica per nome
    Optional<CasaAutomobilistica> findByNome(String nome);
    
    // Trova case automobilistiche per nazione
    List<CasaAutomobilistica> findByNazioneContainingIgnoreCase(String nazione);
    
    // Conta veicoli per casa automobilistica
    @Query("SELECT ca.nome, COUNT(v) FROM CasaAutomobilistica ca LEFT JOIN Veicolo v ON ca.id = v.casaAutomobilistica.id GROUP BY ca.id, ca.nome")
    List<Object[]> contaVeicoliPerCasaAutomobilistica();
}

package com.concessionario.repository;

import com.concessionario.model.Veicolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, Integer> {
    
    // Trova veicoli per marca
    List<Veicolo> findByMarcaContainingIgnoreCase(String marca);
    
    // Trova veicoli per categoria
    List<Veicolo> findByCategoriaId(Integer categoriaId);
    
    // Trova veicoli per casa automobilistica
    List<Veicolo> findByCasaAutomobilisticaId(Integer casaAutomobilisticaId);
    
    // Trova veicoli con prezzo inferiore a un valore
    List<Veicolo> findByPrezzoLessThan(BigDecimal prezzo);
    
    // Trova veicoli disponibili
    List<Veicolo> findByQuantitaDisponibileGreaterThan(Integer quantita);
    
    // Query personalizzata per trovare veicoli per marca e prezzo massimo
    @Query("SELECT v FROM Veicolo v WHERE v.marca = :marca AND v.prezzo <= :prezzoMassimo")
    List<Veicolo> findByMarcaAndPrezzoMassimo(@Param("marca") String marca, @Param("prezzoMassimo") BigDecimal prezzoMassimo);
    
    // Query per calcolare il prezzo medio dei veicoli
    @Query("SELECT AVG(v.prezzo) FROM Veicolo v")
    BigDecimal calcolaPrezzoMedio();
    
    // Query per trovare il veicolo più economico disponibile
    @Query("SELECT v FROM Veicolo v WHERE v.quantitaDisponibile > 0 ORDER BY v.prezzo ASC")
    List<Veicolo> findVeicoliDisponibiliOrdinatiPerPrezzo();
    
    // Query per calcolare il prezzo medio per marca
    @Query("SELECT v.marca, AVG(v.prezzo) FROM Veicolo v GROUP BY v.marca")
    List<Object[]> calcolaPrezzoMedioPerMarca();
    
    // Conta veicoli per categoria
    @Query("SELECT c.nome, COUNT(v) FROM Veicolo v JOIN v.categoria c GROUP BY c.nome")
    List<Object[]> contaVeicoliPerCategoria();
}

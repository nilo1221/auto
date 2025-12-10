package com.concessionario.repository;

import com.concessionario.model.CategoriaVeicolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaVeicoloRepository extends JpaRepository<CategoriaVeicolo, Integer> {
    
    // Trova categoria per nome
    Optional<CategoriaVeicolo> findByNome(String nome);
    
    // Conta veicoli per categoria
    @Query("SELECT c.nome, COUNT(v) FROM CategoriaVeicolo c LEFT JOIN Veicolo v ON c.id = v.categoria.id GROUP BY c.id, c.nome")
    List<Object[]> contaVeicoliPerCategoria();
}

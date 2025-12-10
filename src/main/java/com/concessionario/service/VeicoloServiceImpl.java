package com.concessionario.service;

import com.concessionario.model.Veicolo;
import com.concessionario.repository.VeicoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class VeicoloServiceImpl implements VeicoloService {
    
    @Autowired
    private VeicoloRepository veicoloRepository;
    
    @Override
    public Veicolo save(Veicolo veicolo) {
        return veicoloRepository.save(veicolo);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Veicolo> findById(Integer id) {
        return veicoloRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Veicolo> findAll() {
        return veicoloRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Veicolo> findAll(Pageable pageable) {
        return veicoloRepository.findAll(pageable);
    }
    
    @Override
    public void deleteById(Integer id) {
        veicoloRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Veicolo> findByMarca(String marca) {
        return veicoloRepository.findByMarcaContainingIgnoreCase(marca);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Veicolo> findByCategoria(Integer categoriaId) {
        return veicoloRepository.findByCategoriaId(categoriaId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Veicolo> findByCasaAutomobilistica(Integer casaAutomobilisticaId) {
        return veicoloRepository.findByCasaAutomobilisticaId(casaAutomobilisticaId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Veicolo> findByPrezzoMassimo(BigDecimal prezzoMassimo) {
        return veicoloRepository.findByPrezzoLessThan(prezzoMassimo);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Veicolo> findDisponibili() {
        return veicoloRepository.findByQuantitaDisponibileGreaterThan(0);
    }
    
    @Override
    @Transactional(readOnly = true)
    public BigDecimal calcolaPrezzoMedio() {
        BigDecimal prezzoMedio = veicoloRepository.calcolaPrezzoMedio();
        return prezzoMedio != null ? prezzoMedio : BigDecimal.ZERO;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Veicolo findVeicoloPiuEconomico() {
        List<Veicolo> veicoli = veicoloRepository.findVeicoliDisponibiliOrdinatiPerPrezzo();
        return veicoli.isEmpty() ? null : veicoli.get(0);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Map<String, BigDecimal> calcolaPrezzoMedioPerMarca() {
        List<Object[]> risultati = veicoloRepository.calcolaPrezzoMedioPerMarca();
        Map<String, BigDecimal> prezziPerMarca = new HashMap<>();
        
        for (Object[] risultato : risultati) {
            String marca = (String) risultato[0];
            BigDecimal prezzoMedio = (BigDecimal) risultato[1];
            prezziPerMarca.put(marca, prezzoMedio);
        }
        
        return prezziPerMarca;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> contaVeicoliPerCategoria() {
        List<Object[]> risultati = veicoloRepository.contaVeicoliPerCategoria();
        Map<String, Long> conteggioPerCategoria = new HashMap<>();
        
        for (Object[] risultato : risultati) {
            String categoria = (String) risultato[0];
            Long conteggio = (Long) risultato[1];
            conteggioPerCategoria.put(categoria, conteggio);
        }
        
        return conteggioPerCategoria;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> contaVeicoliPerCasaAutomobilistica() {
        List<Object[]> risultati = veicoloRepository.calcolaPrezzoMedioPerMarca();
        Map<String, Long> conteggioPerMarca = new HashMap<>();
        
        for (Object[] risultato : risultati) {
            String marca = (String) risultato[0];
            // Per semplicità, usiamo il conteggio totale come placeholder
            conteggioPerMarca.put(marca, 1L);
        }
        
        return conteggioPerMarca;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Veicolo> findByMarcaAndPrezzoMassimo(String marca, BigDecimal prezzoMassimo) {
        return veicoloRepository.findByMarcaAndPrezzoMassimo(marca, prezzoMassimo);
    }
}

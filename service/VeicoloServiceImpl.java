package service;

import model.Veicolo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementazione dell'interfaccia VeicoloService con la logica business
 */
public class VeicoloServiceImpl implements VeicoloService {

    @Override
    public double calcolaPrezzoMedio(List<Veicolo> listaVeicoli) {
        if (listaVeicoli == null || listaVeicoli.isEmpty()) {
            return 0.0;
        }
        
        return listaVeicoli.stream()
                .mapToDouble(Veicolo::getPrezzo)
                .average()
                .orElse(0.0);
    }

    @Override
    public Optional<Veicolo> trovaVeicoloDisponibileMenoCaro(List<Veicolo> listaVeicoli) {
        if (listaVeicoli == null || listaVeicoli.isEmpty()) {
            return Optional.empty();
        }
        
        return listaVeicoli.stream()
                .filter(Veicolo::isDisponibile)
                .min((v1, v2) -> Double.compare(v1.getPrezzo(), v2.getPrezzo()));
    }

    @Override
    public double calcolaPrezzoMedioPerMarca(List<Veicolo> listaVeicoli, String marca) {
        if (listaVeicoli == null || listaVeicoli.isEmpty() || marca == null) {
            return 0.0;
        }
        
        return listaVeicoli.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase(marca))
                .mapToDouble(Veicolo::getPrezzo)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Veicolo> filtraVeicoliDisponibili(List<Veicolo> listaVeicoli) {
        if (listaVeicoli == null) {
            return List.of();
        }
        
        return listaVeicoli.stream()
                .filter(Veicolo::isDisponibile)
                .collect(Collectors.toList());
    }

    @Override
    public List<Veicolo> filtraPerMarca(List<Veicolo> listaVeicoli, String marca) {
        if (listaVeicoli == null || marca == null) {
            return List.of();
        }
        
        return listaVeicoli.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase(marca))
                .collect(Collectors.toList());
    }

    @Override
    public String contaVeicoliPerTipo(List<Veicolo> listaVeicoli) {
        if (listaVeicoli == null || listaVeicoli.isEmpty()) {
            return "Nessun veicolo presente";
        }
        
        Map<String, Long> conteggioPerTipo = listaVeicoli.stream()
                .collect(Collectors.groupingBy(Veicolo::getTipoVeicolo, Collectors.counting()));
        
        StringBuilder risultato = new StringBuilder("Statistiche veicoli per tipo:\n");
        conteggioPerTipo.forEach((tipo, conteggio) -> 
            risultato.append(String.format("- %s: %d\n", tipo, conteggio)));
        
        return risultato.toString();
    }
}

package controller;

import model.Veicolo;
import service.VeicoloService;
import service.VeicoloServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 * Controller MVC che gestisce le operazioni sul concessionario
 */
public class ConcessionarioController {
    
    private final VeicoloService veicoloService;
    
    public ConcessionarioController() {
        this.veicoloService = new VeicoloServiceImpl();
    }
    
    /**
     * Calcola e restituisce il prezzo medio di tutti i veicoli
     */
    public String calcolaPrezzoMedio(List<Veicolo> veicoli) {
        double prezzoMedio = veicoloService.calcolaPrezzoMedio(veicoli);
        return String.format("Il prezzo medio di tutti i veicoli è: €%.2f", prezzoMedio);
    }
    
    /**
     * Trova e restituisce il veicolo disponibile meno caro
     */
    public String trovaVeicoloDisponibileMenoCaro(List<Veicolo> veicoli) {
        Optional<Veicolo> veicoloMenoCaro = veicoloService.trovaVeicoloDisponibileMenoCaro(veicoli);
        
        if (veicoloMenoCaro.isPresent()) {
            Veicolo veicolo = veicoloMenoCaro.get();
            return String.format("Veicolo disponibile meno caro: %s %s - €%.2f (Codice: %s)", 
                    veicolo.getMarca(), veicolo.getModello(), veicolo.getPrezzo(), 
                    veicolo.getCodiceImmatricolazione());
        } else {
            return "Nessun veicolo disponibile trovato";
        }
    }
    
    /**
     * Calcola il prezzo medio dei veicoli di una marca specifica
     */
    public String calcolaPrezzoMedioPerMarca(List<Veicolo> veicoli, String marca) {
        double prezzoMedio = veicoloService.calcolaPrezzoMedioPerMarca(veicoli, marca);
        
        if (prezzoMedio > 0) {
            return String.format("Il prezzo medio dei veicoli %s è: €%.2f", marca, prezzoMedio);
        } else {
            return String.format("Nessun veicolo della marca %s trovato", marca);
        }
    }
    
    /**
     * Mostra le statistiche complete del concessionario
     */
    public String mostraStatisticheComplete(List<Veicolo> veicoli) {
        StringBuilder statistiche = new StringBuilder();
        statistiche.append("=== STATISTICHE CONCESSIONARIO ===\n\n");
        
        // Statistiche generali
        statistiche.append(String.format("Numero totale veicoli: %d\n", veicoli.size()));
        
        List<Veicolo> disponibili = veicoloService.filtraVeicoliDisponibili(veicoli);
        statistiche.append(String.format("Veicoli disponibili: %d\n", disponibili.size()));
        
        statistiche.append("\n");
        
        // Prezzo medio
        double prezzoMedio = veicoloService.calcolaPrezzoMedio(veicoli);
        statistiche.append(String.format("Prezzo medio generale: €%.2f\n", prezzoMedio));
        
        // Veicolo meno caro
        Optional<Veicolo> menoCaro = veicoloService.trovaVeicoloDisponibileMenoCaro(veicoli);
        if (menoCaro.isPresent()) {
            Veicolo v = menoCaro.get();
            statistiche.append(String.format("Veicolo disponibile meno caro: %s %s - €%.2f\n", 
                    v.getMarca(), v.getModello(), v.getPrezzo()));
        }
        
        statistiche.append("\n");
        
        // Statistiche per tipo
        statistiche.append(veicoloService.contaVeicoliPerTipo(veicoli));
        
        return statistiche.toString();
    }
    
    /**
     * Filtra e mostra veicoli per marca
     */
    public String filtraMostraPerMarca(List<Veicolo> veicoli, String marca) {
        List<Veicolo> veicoliMarca = veicoloService.filtraPerMarca(veicoli, marca);
        
        if (veicoliMarca.isEmpty()) {
            return String.format("Nessun veicolo della marca %s trovato", marca);
        }
        
        StringBuilder risultato = new StringBuilder();
        risultato.append(String.format("Veicoli della marca %s (%d trovati):\n", marca, veicoliMarca.size()));
        
        for (Veicolo v : veicoliMarca) {
            String disponibilita = v.isDisponibile() ? "Disponibile" : "Non disponibile";
            risultato.append(String.format("- %s %s (%s) - €%.2f - %s\n", 
                    v.getMarca(), v.getModello(), v.getTipoVeicolo(), v.getPrezzo(), disponibilita));
        }
        
        return risultato.toString();
    }
}

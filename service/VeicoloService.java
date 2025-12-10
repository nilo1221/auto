package service;

import model.Veicolo;

import java.util.List;
import java.util.Optional;

/**
 * Interfaccia che definisce i contratti per i calcoli statistici sui veicoli
 */
public interface VeicoloService {
    
    /**
     * Calcola il prezzo medio di tutti i veicoli
     * @param listaVeicoli lista di veicoli
     * @return prezzo medio
     */
    double calcolaPrezzoMedio(List<Veicolo> listaVeicoli);
    
    /**
     * Trova il veicolo disponibile meno caro
     * @param listaVeicoli lista di veicoli
     * @return Optional del veicolo meno caro tra quelli disponibili
     */
    Optional<Veicolo> trovaVeicoloDisponibileMenoCaro(List<Veicolo> listaVeicoli);
    
    /**
     * Calcola il prezzo medio dei veicoli di una certa marca
     * @param listaVeicoli lista di veicoli
     * @param marca marca da cercare
     * @return prezzo medio dei veicoli della marca specificata
     */
    double calcolaPrezzoMedioPerMarca(List<Veicolo> listaVeicoli, String marca);
    
    /**
     * Filtra i veicoli disponibili
     * @param listaVeicoli lista di veicoli
     * @return lista dei veicoli disponibili
     */
    List<Veicolo> filtraVeicoliDisponibili(List<Veicolo> listaVeicoli);
    
    /**
     * Filtra i veicoli per marca
     * @param listaVeicoli lista di veicoli
     * @param marca marca da filtrare
     * @return lista dei veicoli della marca specificata
     */
    List<Veicolo> filtraPerMarca(List<Veicolo> listaVeicoli, String marca);
    
    /**
     * Conta i veicoli per tipo
     * @param listaVeicoli lista di veicoli
     * @return statistica dei veicoli per tipo
     */
    String contaVeicoliPerTipo(List<Veicolo> listaVeicoli);
}

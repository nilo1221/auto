import controller.ConcessionarioController;
import model.Automobile;
import model.Furgone;
import model.Motocicletta;
import model.Veicolo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principale che avvia l'applicazione concessionario
 * Pattern MVC: View (Console) -> Controller -> Service -> Model
 */
public class ConcessionarioMain {
    
    private static ConcessionarioController controller;
    private static List<Veicolo> veicoli;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        // Inizializzazione
        controller = new ConcessionarioController();
        veicoli = new ArrayList<>();
        scanner = new Scanner(System.in);
        
        // Carica dati di esempio
        caricaDatiEsempio();
        
        // Menu principale
        boolean running = true;
        while (running) {
            mostraMenu();
            int scelta = leggiIntero("Scelta: ");
            
            switch (scelta) {
                case 1:
                    System.out.println("\n" + controller.mostraStatisticheComplete(veicoli));
                    break;
                case 2:
                    System.out.println("\n" + controller.calcolaPrezzoMedio(veicoli));
                    break;
                case 3:
                    System.out.println("\n" + controller.trovaVeicoloDisponibileMenoCaro(veicoli));
                    break;
                case 4:
                    String marca = leggiStringa("Inserisci la marca: ");
                    System.out.println("\n" + controller.calcolaPrezzoMedioPerMarca(veicoli, marca));
                    break;
                case 5:
                    String marcaFiltro = leggiStringa("Inserisci la marca da filtrare: ");
                    System.out.println("\n" + controller.filtraMostraPerMarca(veicoli, marcaFiltro));
                    break;
                case 6:
                    mostraElencoVeicoli();
                    break;
                case 7:
                    aggiungiNuovoVeicolo();
                    break;
                case 0:
                    running = false;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
            
            if (running) {
                System.out.println("\nPremi Invio per continuare...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void mostraMenu() {
        System.out.println("\n=== CONCESSIONARIO AUTO ===");
        System.out.println("1. Mostra statistiche complete");
        System.out.println("2. Calcola prezzo medio di tutti i veicoli");
        System.out.println("3. Trova veicolo disponibile meno caro");
        System.out.println("4. Calcola prezzo medio per marca");
        System.out.println("5. Filtra veicoli per marca");
        System.out.println("6. Mostra elenco completo veicoli");
        System.out.println("7. Aggiungi nuovo veicolo");
        System.out.println("0. Esci");
    }
    
    private static void caricaDatiEsempio() {
        // Automobile
        Automobile auto1 = new Automobile(1L, "Panda", "Fiat", "AB123CD", 
                LocalDate.of(2020, 5, 15), 8500.0, 5, "Benzina", 1200);
        Automobile auto2 = new Automobile(2L, "Golf", "Volkswagen", "EF456GH", 
                LocalDate.of(2021, 8, 20), 18500.0, 5, "Diesel", 2000);
        Automobile auto3 = new Automobile(3L, "Clio", "Renault", "IJ789KL", 
                LocalDate.of(2019, 3, 10), 7200.0, 3, "Benzina", 1400);
        auto3.setDisponibile(false); // Non disponibile
        
        // Motociclette
        Motocicletta moto1 = new Motocicletta(4L, "Monster", "Ducati", "MN234OP", 
                LocalDate.of(2022, 6, 1), 12500.0, 800, "2 tempi", true);
        Motocicletta moto2 = new Motocicletta(5L, "CBR", "Honda", "QR567ST", 
                LocalDate.of(2021, 9, 12), 9800.0, 600, "4 tempi", false);
        
        // Furgoni
        Furgone furgone1 = new Furgone(6L, "Transit", "Ford", "UV890WX", 
                LocalDate.of(2020, 11, 30), 15500.0, 12.5, 3, true);
        Furgone furgone2 = new Furgone(7L, "Ducato", "Fiat", "YZ123AB", 
                LocalDate.of(2022, 2, 18), 18900.0, 15.0, 3, true);
        furgone2.setDisponibile(false); // Non disponibile
        
        veicoli.add(auto1);
        veicoli.add(auto2);
        veicoli.add(auto3);
        veicoli.add(moto1);
        veicoli.add(moto2);
        veicoli.add(furgone1);
        veicoli.add(furgone2);
        
        System.out.println("Dati di esempio caricati: " + veicoli.size() + " veicoli");
    }
    
    private static void mostraElencoVeicoli() {
        System.out.println("\n=== ELENCO COMPLETO VEICOLI ===");
        for (Veicolo v : veicoli) {
            String disponibilita = v.isDisponibile() ? "Disponibile" : "Non disponibile";
            System.out.printf("- %s %s (%s) - %s - €%.2f - %s\n", 
                    v.getMarca(), v.getModello(), v.getTipoVeicolo(), 
                    v.getCodiceImmatricolazione(), v.getPrezzo(), disponibilita);
        }
    }
    
    private static void aggiungiNuovoVeicolo() {
        System.out.println("\n=== AGGIUNGI NUOVO VEICOLO ===");
        System.out.println("1. Automobile");
        System.out.println("2. Motocicletta");
        System.out.println("3. Furgone");
        
        int tipo = leggiIntero("Tipo veicolo: ");
        
        // Dati comuni
        Long id = (long) (veicoli.size() + 1);
        String marca = leggiStringa("Marca: ");
        String modello = leggiStringa("Modello: ");
        String codice = leggiStringa("Codice immatricolazione: ");
        System.out.print("Data immatricolazione (yyyy-mm-dd): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        Double prezzo = leggiDouble("Prezzo: ");
        
        Veicolo nuovoVeicolo = null;
        
        switch (tipo) {
            case 1: // Automobile
                Integer porte = leggiIntero("Numero porte: ");
                System.out.print("Tipo carburante: ");
                String carburante = scanner.nextLine();
                Integer cilindrata = leggiIntero("Cilindrata: ");
                nuovoVeicolo = new Automobile(id, modello, marca, codice, data, prezzo, 
                        porte, carburante, cilindrata);
                break;
            case 2: // Motocicletta
                Integer cilMoto = leggiIntero("Cilindrata: ");
                System.out.print("Tipo motore: ");
                String tipoMotore = scanner.nextLine();
                Boolean haBauletto = leggiBoolean("Ha bauletto? (true/false): ");
                nuovoVeicolo = new Motocicletta(id, modello, marca, codice, data, prezzo, 
                        cilMoto, tipoMotore, haBauletto);
                break;
            case 3: // Furgone
                Double capacita = leggiDouble("Capacità carico (mc): ");
                Integer posti = leggiIntero("Numero posti: ");
                Boolean haPortellone = leggiBoolean("Ha portellone laterale? (true/false): ");
                nuovoVeicolo = new Furgone(id, modello, marca, codice, data, prezzo, 
                        capacita, posti, haPortellone);
                break;
            default:
                System.out.println("Tipo veicolo non valido");
                return;
        }
        
        veicoli.add(nuovoVeicolo);
        System.out.println("Veicolo aggiunto con successo!");
    }
    
    // Metodi di utilità per la lettura input
    private static String leggiStringa(String messaggio) {
        System.out.print(messaggio);
        return scanner.nextLine();
    }
    
    private static int leggiIntero(String messaggio) {
        while (true) {
            try {
                System.out.print(messaggio);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valore non valido. Inserisci un numero intero.");
            }
        }
    }
    
    private static double leggiDouble(String messaggio) {
        while (true) {
            try {
                System.out.print(messaggio);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valore non valido. Inserisci un numero decimale.");
            }
        }
    }
    
    private static boolean leggiBoolean(String messaggio) {
        while (true) {
            try {
                System.out.print(messaggio);
                return Boolean.parseBoolean(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Valore non valido. Inserisci true o false.");
            }
        }
    }
}

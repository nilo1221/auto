package model;

import java.time.LocalDate;

/**
 * Classe figlia che rappresenta una motocicletta
 */
public class Motocicletta extends Veicolo {
    private Integer cilindrata;
    private String tipoMotore;
    private Boolean haBauletto;

    public Motocicletta() {
        super();
    }

    public Motocicletta(Long id, String modello, String marca, String codiceImmatricolazione, 
                        LocalDate dataImmatricolazione, Double prezzo, Integer cilindrata, 
                        String tipoMotore, Boolean haBauletto) {
        super(id, modello, marca, codiceImmatricolazione, dataImmatricolazione, prezzo);
        this.cilindrata = cilindrata;
        this.tipoMotore = tipoMotore;
        this.haBauletto = haBauletto;
    }

    // Getters e Setters specifici della motocicletta
    public Integer getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(Integer cilindrata) {
        this.cilindrata = cilindrata;
    }

    public String getTipoMotore() {
        return tipoMotore;
    }

    public void setTipoMotore(String tipoMotore) {
        this.tipoMotore = tipoMotore;
    }

    public Boolean getHaBauletto() {
        return haBauletto;
    }

    public void setHaBauletto(Boolean haBauletto) {
        this.haBauletto = haBauletto;
    }

    @Override
    public String getTipoVeicolo() {
        return "Motocicletta";
    }

    @Override
    public String toString() {
        return "Motocicletta{" +
                "cilindrata=" + cilindrata +
                ", tipoMotore='" + tipoMotore + '\'' +
                ", haBauletto=" + haBauletto +
                "} " + super.toString();
    }
}

package model;

import java.time.LocalDate;

/**
 * Classe figlia che rappresenta un furgone
 */
public class Furgone extends Veicolo {
    private Double capacitaCarico; // in metri cubi
    private Integer numeroPosti;
    private Boolean haPortelloneLaterale;

    public Furgone() {
        super();
    }

    public Furgone(Long id, String modello, String marca, String codiceImmatricolazione, 
                   LocalDate dataImmatricolazione, Double prezzo, Double capacitaCarico, 
                   Integer numeroPosti, Boolean haPortelloneLaterale) {
        super(id, modello, marca, codiceImmatricolazione, dataImmatricolazione, prezzo);
        this.capacitaCarico = capacitaCarico;
        this.numeroPosti = numeroPosti;
        this.haPortelloneLaterale = haPortelloneLaterale;
    }

    // Getters e Setters specifici del furgone
    public Double getCapacitaCarico() {
        return capacitaCarico;
    }

    public void setCapacitaCarico(Double capacitaCarico) {
        this.capacitaCarico = capacitaCarico;
    }

    public Integer getNumeroPosti() {
        return numeroPosti;
    }

    public void setNumeroPosti(Integer numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    public Boolean getHaPortelloneLaterale() {
        return haPortelloneLaterale;
    }

    public void setHaPortelloneLaterale(Boolean haPortelloneLaterale) {
        this.haPortelloneLaterale = haPortelloneLaterale;
    }

    @Override
    public String getTipoVeicolo() {
        return "Furgone";
    }

    @Override
    public String toString() {
        return "Furgone{" +
                "capacitaCarico=" + capacitaCarico +
                ", numeroPosti=" + numeroPosti +
                ", haPortelloneLaterale=" + haPortelloneLaterale +
                "} " + super.toString();
    }
}

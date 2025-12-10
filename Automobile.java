package model;

import java.time.LocalDate;

/**
 * Classe figlia che rappresenta un'automobile
 */
public class Automobile extends Veicolo {
    private Integer numeroPorte;
    private String tipoCarburante;
    private Integer cilindrata;

    public Automobile() {
        super();
    }

    public Automobile(Long id, String modello, String marca, String codiceImmatricolazione, 
                     LocalDate dataImmatricolazione, Double prezzo, Integer numeroPorte, 
                     String tipoCarburante, Integer cilindrata) {
        super(id, modello, marca, codiceImmatricolazione, dataImmatricolazione, prezzo);
        this.numeroPorte = numeroPorte;
        this.tipoCarburante = tipoCarburante;
        this.cilindrata = cilindrata;
    }

    // Getters e Setters specifici dell'automobile
    public Integer getNumeroPorte() {
        return numeroPorte;
    }

    public void setNumeroPorte(Integer numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    public String getTipoCarburante() {
        return tipoCarburante;
    }

    public void setTipoCarburante(String tipoCarburante) {
        this.tipoCarburante = tipoCarburante;
    }

    public Integer getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(Integer cilindrata) {
        this.cilindrata = cilindrata;
    }

    @Override
    public String getTipoVeicolo() {
        return "Automobile";
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "numeroPorte=" + numeroPorte +
                ", tipoCarburante='" + tipoCarburante + '\'' +
                ", cilindrata=" + cilindrata +
                "} " + super.toString();
    }
}

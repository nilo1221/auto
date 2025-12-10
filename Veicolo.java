package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe padre che rappresenta un veicolo generico
 */
public abstract class Veicolo {
    private Long id;
    private String modello;
    private String marca;
    private String codiceImmatricolazione;
    private LocalDate dataImmatricolazione;
    private Double prezzo;
    private boolean disponibile;

    public Veicolo() {
        this.disponibile = true;
    }

    public Veicolo(Long id, String modello, String marca, String codiceImmatricolazione, 
                   LocalDate dataImmatricolazione, Double prezzo) {
        this.id = id;
        this.modello = modello;
        this.marca = marca;
        this.codiceImmatricolazione = codiceImmatricolazione;
        this.dataImmatricolazione = dataImmatricolazione;
        this.prezzo = prezzo;
        this.disponibile = true;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodiceImmatricolazione() {
        return codiceImmatricolazione;
    }

    public void setCodiceImmatricolazione(String codiceImmatricolazione) {
        this.codiceImmatricolazione = codiceImmatricolazione;
    }

    public LocalDate getDataImmatricolazione() {
        return dataImmatricolazione;
    }

    public void setDataImmatricolazione(LocalDate dataImmatricolazione) {
        this.dataImmatricolazione = dataImmatricolazione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    // Metodo astratto che deve essere implementato dalle classi figlie
    public abstract String getTipoVeicolo();

    // Metodo per calcolare l'età del veicolo in anni
    public int getEtaVeicolo() {
        return LocalDate.now().getYear() - dataImmatricolazione.getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veicolo veicolo = (Veicolo) o;
        return Objects.equals(codiceImmatricolazione, veicolo.codiceImmatricolazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiceImmatricolazione);
    }

    @Override
    public String toString() {
        return "Veicolo{" +
                "id=" + id +
                ", modello='" + modello + '\'' +
                ", marca='" + marca + '\'' +
                ", codiceImmatricolazione='" + codiceImmatricolazione + '\'' +
                ", dataImmatricolazione=" + dataImmatricolazione +
                ", prezzo=" + prezzo +
                ", disponibile=" + disponibile +
                '}';
    }
}

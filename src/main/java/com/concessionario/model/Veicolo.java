package com.concessionario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "veicolo")
public class Veicolo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "La marca è obbligatoria")
    @Size(max = 100, message = "La marca non può superare 100 caratteri")
    @Column(name = "marca", nullable = false, length = 100)
    private String marca;
    
    @NotBlank(message = "Il modello è obbligatorio")
    @Size(max = 100, message = "Il modello non può superare 100 caratteri")
    @Column(name = "modello", nullable = false, length = 100)
    private String modello;
    
    @Min(value = 1900, message = "L'anno di produzione non può essere precedente al 1900")
    @Column(name = "anno_produzione")
    private Integer annoProduzione = 2024;
    
    @NotNull(message = "La categoria è obbligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaVeicolo categoria;
    
    @NotNull(message = "La casa automobilistica è obbligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "casa_automobilistica_id", nullable = false)
    private CasaAutomobilistica casaAutomobilistica;
    
    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo deve essere maggiore di 0")
    @Column(name = "prezzo", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzo;
    
    @Min(value = 0, message = "La quantità disponibile non può essere negativa")
    @Column(name = "quantita_disponibile")
    private Integer quantitaDisponibile = 1;
    
    @Size(max = 500, message = "L'URL dell'immagine non può superare 500 caratteri")
    @Column(name = "immagine", length = 500)
    private String immagine;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Getters e Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getModello() {
        return modello;
    }
    
    public void setModello(String modello) {
        this.modello = modello;
    }
    
    public Integer getAnnoProduzione() {
        return annoProduzione;
    }
    
    public void setAnnoProduzione(Integer annoProduzione) {
        this.annoProduzione = annoProduzione;
    }
    
    public CategoriaVeicolo getCategoria() {
        return categoria;
    }
    
    public void setCategoria(CategoriaVeicolo categoria) {
        this.categoria = categoria;
    }
    
    public CasaAutomobilistica getCasaAutomobilistica() {
        return casaAutomobilistica;
    }
    
    public void setCasaAutomobilistica(CasaAutomobilistica casaAutomobilistica) {
        this.casaAutomobilistica = casaAutomobilistica;
    }
    
    public BigDecimal getPrezzo() {
        return prezzo;
    }
    
    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
    
    public Integer getQuantitaDisponibile() {
        return quantitaDisponibile;
    }
    
    public void setQuantitaDisponibile(Integer quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
    }
    
    public String getImmagine() {
        return immagine;
    }
    
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

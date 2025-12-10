package com.concessionario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "casa_automobilistica")
public class CasaAutomobilistica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "Il nome della casa automobilistica è obbligatorio")
    @Size(max = 100, message = "Il nome non può superare 100 caratteri")
    @Column(name = "nome", nullable = false, unique = true, length = 100)
    private String nome;
    
    @Size(max = 50, message = "La nazione non può superare 50 caratteri")
    @Column(name = "nazione", length = 50)
    private String nazione;
    
    @Size(max = 255, message = "Il sito web non può superare 255 caratteri")
    @Column(name = "sito_web", length = 255)
    private String sitoWeb;
    
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
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNazione() {
        return nazione;
    }
    
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
    
    public String getSitoWeb() {
        return sitoWeb;
    }
    
    public void setSitoWeb(String sitoWeb) {
        this.sitoWeb = sitoWeb;
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

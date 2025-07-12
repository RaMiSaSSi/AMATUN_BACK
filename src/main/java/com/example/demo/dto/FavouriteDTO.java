// File: src/main/java/com/example/demo/dto/FavouriteDTO.java
package com.example.demo.dto;

public class FavouriteDTO {
    private Long id;
    private Long utilisateurId;
    private Long produitId;

    // Constructors
    public FavouriteDTO() {}

    public FavouriteDTO(Long id, Long utilisateurId, Long produitId) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.produitId = produitId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }
}
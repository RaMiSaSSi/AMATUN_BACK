// dto/SousCategorieDTO.java
package com.example.demo.dto;

import com.example.demo.model.SousCategorie;

public class SousCategorieDTO {
    private long id;
    private String nom;
    private Long categorieId;
    private Long boutiqueId;


    public SousCategorieDTO() {
    }

    public SousCategorieDTO(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }
// Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public Long getBoutiqueId() {
        return boutiqueId;
    }

    public void setBoutiqueId(Long boutiqueId) {
        this.boutiqueId = boutiqueId;
    }

    public SousCategorie toEntity() {
        SousCategorie sousCategorie = new SousCategorie();
        sousCategorie.setId(this.id);
        sousCategorie.setNom(this.nom);
        return sousCategorie;
    }
}
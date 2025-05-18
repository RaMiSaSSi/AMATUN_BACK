// Categorie.java
package com.example.demo.model;

import com.example.demo.dto.CategorieDTO;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    @Lob
    private byte[] image;
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private Set<BoutiqueCategorie> boutiqueCategories;
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private Set<SousCategorie> sousCategories;
    private String imagePath;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<BoutiqueCategorie> getBoutiqueCategories() {
        return boutiqueCategories;
    }

    public void setBoutiqueCategories(Set<BoutiqueCategorie> boutiqueCategories) {
        this.boutiqueCategories = boutiqueCategories;
    }

    public Set<SousCategorie> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(Set<SousCategorie> sousCategories) {
        this.sousCategories = sousCategories;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public CategorieDTO getDTO() {
        CategorieDTO dto = new CategorieDTO();
        dto.setId(this.id);
        dto.setNom(this.nom);
        dto.setImage(this.image);
        dto.setImagePath(this.imagePath);
        return dto;
    }


}
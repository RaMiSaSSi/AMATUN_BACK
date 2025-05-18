// dto/CategorieDTO.java
package com.example.demo.dto;

import com.example.demo.model.Boutique;
import com.example.demo.model.Categorie;

import java.util.List;

public class CategorieDTO {
    private long id;
    private String nom;
    private byte[] image;
    private List<BoutiqueCategorieDTO> boutiqueCategories;
    private String imagePath;


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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<BoutiqueCategorieDTO> getBoutiqueCategories() {
        return boutiqueCategories;
    }

    public void setBoutiqueCategories(List<BoutiqueCategorieDTO> boutiqueCategories) {
        this.boutiqueCategories = boutiqueCategories;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Categorie toEntity() {
        Categorie categorie = new Categorie();
        categorie.setId(this.id);
        categorie.setNom(this.nom);
        categorie.setImage(this.image);
        return categorie;
    }
}
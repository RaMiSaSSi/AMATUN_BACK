// File: src/main/java/com/example/demo/dto/ProduitDTO.java
package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProduitDTO {
    private Long id;
    private String nom;
    private byte[] image;
    private String description;
    private double prix;
    private int quantite;
    private Long categorieId;
    private Long sousCategorieId;
    private Long boutiqueId;
    private boolean promo;
    private double promotionPercentage;
    private int duree;
    private LocalDate startDate;
    private String imagePath;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public Long getSousCategorieId() {
        return sousCategorieId;
    }

    public void setSousCategorieId(Long sousCategorieId) {
        this.sousCategorieId = sousCategorieId;
    }

    public Long getBoutiqueId() {
        return boutiqueId;
    }

    public void setBoutiqueId(Long boutiqueId) {
        this.boutiqueId = boutiqueId;
    }

    public boolean isPromo() {
        return promo;
    }

    public void setPromo(boolean promo) {
        this.promo = promo;
    }

    public double getPromotionPercentage() {
        return promotionPercentage;
    }

    public void setPromotionPercentage(double promotionPercentage) {
        this.promotionPercentage = promotionPercentage;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
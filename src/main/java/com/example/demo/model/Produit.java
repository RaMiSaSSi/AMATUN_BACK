// File: src/main/java/com/example/demo/model/Produit.java
package com.example.demo.model;

import com.example.demo.dto.ProduitDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    //@Lob
    //private byte[] image;
    private String description;
    private double prix;
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "sous_categorie_id")
    private SousCategorie sousCategorie;

    @ManyToOne
    @JoinColumn(name = "boutique_id")
    private Boutique boutique;
    private boolean promo;
    private double promotionPercentage;
    private int duree;
    private LocalDate startDate;
    private String imagePath;



    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommandeProduit> commandeProduits;

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

    /*public byte[] getImage() {
        return image;
    }*/

    /*public void setImage(byte[] image) {
        this.image = image;
    }*/

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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public SousCategorie getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    public List<CommandeProduit> getCommandeProduits() {
        return commandeProduits;
    }

    public void setCommandeProduits(List<CommandeProduit> commandeProduits) {
        this.commandeProduits = commandeProduits;
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

    public ProduitDTO toDTO() {
        ProduitDTO dto = new ProduitDTO();
        dto.setId(this.id);
        dto.setNom(this.nom);
        /*dto.setImage(this.image);*/
        dto.setDescription(this.description);
        dto.setPrix(this.prix);
        dto.setQuantite(this.quantite);
        if (this.categorie != null) {
            dto.setCategorieId(this.categorie.getId());
        }
        if (this.sousCategorie != null) {
            dto.setSousCategorieId(this.sousCategorie.getId());
        }
        if (this.boutique != null) {
            dto.setBoutiqueId(this.boutique.getId());
        }
        dto.setPromo(this.promo);
        dto.setPromotionPercentage(this.promotionPercentage);
        dto.setDuree(this.duree);
        dto.setStartDate(this.startDate);
        dto.setImagePath(this.imagePath);
        return dto;
    }
}
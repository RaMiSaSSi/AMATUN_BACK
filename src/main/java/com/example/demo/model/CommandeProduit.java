package com.example.demo.model;

import com.example.demo.dto.CommandeProduitDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CommandeProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    private int quantite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public CommandeProduitDTO toDTO() {
        CommandeProduitDTO dto = new CommandeProduitDTO();
        dto.setId(this.id);
        dto.setProduitId(this.produit.getId());
        dto.setQuantite(this.quantite);
        return dto;
    }
}
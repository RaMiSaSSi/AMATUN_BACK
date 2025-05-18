// BoutiqueCategorie.java
package com.example.demo.model;

import com.example.demo.dto.CategorieDTO;
import jakarta.persistence.*;

@Entity
public class BoutiqueCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Boutique boutique;
    @ManyToOne
    private Categorie categorie;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public CategorieDTO toDTO() {
        CategorieDTO categorieDTO = new CategorieDTO();
        categorieDTO.setId(this.categorie.getId());
        categorieDTO.setNom(this.categorie.getNom());
        return categorieDTO;
    }
}
package com.example.demo.dto;

import java.util.List;

public class BoutiqueCategorieDTO {
    private long id;
    private long boutiqueId;
    private long categorieId;
    private List<BoutiqueCategorieDTO> boutiqueCategories;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBoutiqueId() {
        return boutiqueId;
    }

    public void setBoutiqueId(long boutiqueId) {
        this.boutiqueId = boutiqueId;
    }

    public long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(long categorieId) {
        this.categorieId = categorieId;
    }

    public List<BoutiqueCategorieDTO> getBoutiqueCategories() {
        return boutiqueCategories;
    }

    public void setBoutiqueCategories(List<BoutiqueCategorieDTO> boutiqueCategories) {
        this.boutiqueCategories = boutiqueCategories;
    }
}
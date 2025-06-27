package com.example.demo.model;

import com.example.demo.dto.PersonnelBoutiqueDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("PERSONNEL_BOUTIQUE")
public class PersonnelBoutique extends UtilisateurInscrit {
    private String username;
    @ManyToOne
    @JoinColumn(name = "boutique_id")
    private Boutique boutique;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }
    public PersonnelBoutiqueDTO toDTO() {
        PersonnelBoutiqueDTO dto = new PersonnelBoutiqueDTO();
        dto.setId(this.getId());
        dto.setNom(this.getNom());
        dto.setPrenom(this.getPrenom());
        dto.setEmail(this.getEmail());
        dto.setTelephone(this.getTelephone());
        dto.setUsername(this.username);
        dto.setBoutiqueId(this.boutique != null ? this.boutique.getId() : null);
        return dto;
    }
}
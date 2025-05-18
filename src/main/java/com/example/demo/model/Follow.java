package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private UtilisateurInscrit utilisateur;

    @ManyToOne
    @JoinColumn(name = "boutique_id", nullable = false)
    private Boutique boutique;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UtilisateurInscrit getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurInscrit utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }
}
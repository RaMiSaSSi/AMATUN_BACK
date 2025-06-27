// src/main/java/com/example/demo/dto/UtilisateurInscritDTO.java
package com.example.demo.dto;

import com.example.demo.model.Role;

import java.util.Date;
import java.util.List;

public class UtilisateurInscritDTO {
    private long id;
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String telephone;
    private AdresseDTO adresseLivraison;
    private Date dateInscription;
    private Role role;
    private List<Long> followedBoutiqueIds; // Add this line
    private boolean firstLogin = false;
    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public AdresseDTO getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(AdresseDTO adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Long> getFollowedBoutiqueIds() {
        return followedBoutiqueIds;
    }

    public void setFollowedBoutiqueIds(List<Long> followedBoutiqueIds) {
        this.followedBoutiqueIds = followedBoutiqueIds;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
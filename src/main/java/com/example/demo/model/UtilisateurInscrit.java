// src/main/java/com/example/demo/model/UtilisateurInscrit.java
package com.example.demo.model;

import com.example.demo.dto.UtilisateurInscritDTO;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public class UtilisateurInscrit  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String telephone;
    private Date dateInscription;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresseLivraison;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> followedBoutiques = new HashSet<>();


    // Getters and setters
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

    public Adresse getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(Adresse adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
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

    public Set<Follow> getFollowedBoutiques() {
        return followedBoutiques;
    }

    public void setFollowedBoutiques(Set<Follow> followedBoutiques) {
        this.followedBoutiques = followedBoutiques;
    }

    public UtilisateurInscritDTO getDTO() {
        UtilisateurInscritDTO dto = new UtilisateurInscritDTO();
        dto.setId(id);
        dto.setEmail(email);
        dto.setMotDePasse(motDePasse);
        dto.setNom(nom);
        dto.setPrenom(prenom);
        dto.setTelephone(telephone);
        dto.setDateInscription(dateInscription);
        dto.setRole(role);
        dto.setAdresseLivraison(adresseLivraison.toDTO());

        return dto;
    }
}
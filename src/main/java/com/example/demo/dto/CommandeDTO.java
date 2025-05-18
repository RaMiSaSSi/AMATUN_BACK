package com.example.demo.dto;

    import com.example.demo.model.Statut;
    import lombok.Data;

    import java.time.LocalDateTime;
    import java.util.List;

    @Data
    public class CommandeDTO {
        private Long id;
        private Long clientId; // Pour les utilisateurs inscrits
        private String nom; // Pour les utilisateurs non inscrits
        private String prenom; // Pour les utilisateurs non inscrits
        private AdresseDTO adresse; // Pour les utilisateurs non inscrits
        private String numTel; // Pour les utilisateurs non inscrits
        private List<CommandeProduitDTO> produits;
        private double prixTotalSansLivraison;
        private double prixTotalAvecLivraison;
        private Statut statut; // New attribute
        private LocalDateTime date; // New attribute

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getClientId() {
            return clientId;
        }

        public void setClientId(Long clientId) {
            this.clientId = clientId;
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

        public AdresseDTO getAdresse() {
            return adresse;
        }

        public void setAdresse(AdresseDTO adresse) {
            this.adresse = adresse;
        }

        public String getNumTel() {
            return numTel;
        }

        public void setNumTel(String numTel) {
            this.numTel = numTel;
        }

        public List<CommandeProduitDTO> getProduits() {
            return produits;
        }

        public void setProduits(List<CommandeProduitDTO> produits) {
            this.produits = produits;
        }

        public double getPrixTotalSansLivraison() {
            return prixTotalSansLivraison;
        }

        public void setPrixTotalSansLivraison(double prixTotalSansLivraison) {
            this.prixTotalSansLivraison = prixTotalSansLivraison;
        }

        public double getPrixTotalAvecLivraison() {
            return prixTotalAvecLivraison;
        }

        public void setPrixTotalAvecLivraison(double prixTotalAvecLivraison) {
            this.prixTotalAvecLivraison = prixTotalAvecLivraison;
        }

        public Statut getStatut() {
            return statut;
        }

        public void setStatut(Statut statut) {
            this.statut = statut;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }
    }
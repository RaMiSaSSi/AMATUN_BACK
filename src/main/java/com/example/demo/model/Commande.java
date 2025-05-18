// File: src/main/java/com/example/demo/model/Commande.java

                        package com.example.demo.model;

                        import com.example.demo.dto.AdresseDTO;
                        import com.example.demo.dto.CommandeDTO;
                        import jakarta.persistence.*;
                        import lombok.Data;

                        import java.time.LocalDateTime;
                        import java.util.List;
                        import java.util.stream.Collectors;

                        @Data
                        @Entity
                        public class Commande {
                            @Id
                            @GeneratedValue(strategy = GenerationType.IDENTITY)
                            private Long id;

                            @ManyToOne
                            @JoinColumn(name = "UtilisateurInscrit_id")
                            private UtilisateurInscrit client;

                            private String nom; // Pour les utilisateurs non inscrits
                            private String prenom; // Pour les utilisateurs non inscrits

                            @ManyToOne
                            @JoinColumn(name = "Adresse_id")
                            private Adresse adresse; // Pour les utilisateurs non inscrits

                            private String numTel; // Pour les utilisateurs non inscrits

                            @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
                            private List<CommandeProduit> produits;

                            private double prixTotalSansLivraison;
                            private double prixTotalAvecLivraison;

                            @Enumerated(EnumType.STRING)
                            private Statut statut = Statut.PENDING; // Default value

                            private LocalDateTime date = LocalDateTime.now(); // Default value

                            public Long getId() {
                                return id;
                            }

                            public void setId(Long id) {
                                this.id = id;
                            }

                            public UtilisateurInscrit getClient() {
                                return client;
                            }

                            public void setClient(UtilisateurInscrit client) {
                                this.client = client;
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

                            public Adresse getAdresse() {
                                return adresse;
                            }

                            public void setAdresse(Adresse adresse) {
                                this.adresse = adresse;
                            }

                            public String getNumTel() {
                                return numTel;
                            }

                            public void setNumTel(String numTel) {
                                this.numTel = numTel;
                            }

                            public List<CommandeProduit> getProduits() {
                                return produits;
                            }

                            public void setProduits(List<CommandeProduit> produits) {
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

                            public CommandeDTO toDTO() {
                                CommandeDTO dto = new CommandeDTO();
                                dto.setId(this.id);
                                if (this.client != null) {
                                    dto.setClientId(this.client.getId());
                                    dto.setPrenom(this.client.getPrenom());
                                    dto.setNom(this.client.getNom());
                                    if (this.client.getAdresseLivraison() != null) {
                                        dto.setAdresse(this.client.getAdresseLivraison().toDTO());
                                    } else {
                                        dto.setAdresse(null); // Or handle the null case as needed
                                    }
                                    dto.setNumTel(this.client.getTelephone());
                                } else {
                                    dto.setNom(this.nom);
                                    dto.setPrenom(this.prenom);
                                    dto.setAdresse(this.adresse != null ? this.adresse.toDTO() : null);
                                    dto.setNumTel(this.numTel);
                                }
                                dto.setProduits(this.produits.stream().map(CommandeProduit::toDTO).collect(Collectors.toList()));
                                dto.setPrixTotalSansLivraison(this.prixTotalSansLivraison);
                                dto.setPrixTotalAvecLivraison(this.prixTotalAvecLivraison);
                                dto.setStatut(this.statut);
                                dto.setDate(this.date);
                                return dto;
                            }
                        }
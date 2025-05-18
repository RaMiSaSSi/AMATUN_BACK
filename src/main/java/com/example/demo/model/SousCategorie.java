// SousCategorie.java
        package com.example.demo.model;

        import com.example.demo.dto.SousCategorieDTO;
        import jakarta.persistence.*;

        import java.util.List;

@Entity
        public class SousCategorie {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private long id;
            private String nom;
            @Lob
            private byte[] image;
            @ManyToOne
            @JoinColumn(name = "categorie_id")
            private Categorie categorie;
    @OneToMany(mappedBy = "sousCategorie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Produit> produits;
            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getNom() {
                return nom;
            }

            public void setNom(String nom) {
                this.nom = nom;
            }

            public byte[] getImage() {
                return image;
            }

            public void setImage(byte[] image) {
                this.image = image;
            }

            public Categorie getCategorie() {
                return categorie;
            }

            public void setCategorie(Categorie categorie) {
                this.categorie = categorie;
            }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public SousCategorieDTO toDTO() {
                SousCategorieDTO dto = new SousCategorieDTO(this.id, this.nom, this.image);
                if (this.categorie != null) {
                    dto.setCategorieId(this.categorie.getId());
                    // Retrieve the boutiqueId through the BoutiqueCategorie relationship
                    this.categorie.getBoutiqueCategories().stream()
                        .findFirst()
                        .ifPresent(boutiqueCategorie -> dto.setBoutiqueId(boutiqueCategorie.getBoutique().getId()));
                }
                return dto;
            }
        }
// File: src/main/java/com/example/demo/model/Boutique.java
    package com.example.demo.model;

    import com.example.demo.dto.BoutiqueDTO;
    import jakarta.persistence.*;
    import lombok.Data;

    import java.util.HashSet;
    import java.util.Set;
    import java.util.stream.Collectors;

    @Entity
    @Data
    @Table(name = "boutiques")
    public class Boutique {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String nom;
        private String telephone;
        private String email;
        private int views;
        @Lob
        private byte[] image;

        @Lob
        private byte[] banner;

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "adresse_id", nullable = false)
        private Adresse adresse;

        @OneToMany(mappedBy = "boutique", cascade = CascadeType.ALL)
        private Set<BoutiqueCategorie> boutiqueCategories = new HashSet<>();

        @OneToMany(mappedBy = "boutique", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<PersonnelBoutique> personnelBoutiques = new HashSet<>();

        @OneToMany(mappedBy = "boutique", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<Follow> followers = new HashSet<>();
        private String imagePath;
        private String bannerPath;
        @Column(name = "category_shop_id")
        private Long categoryShopId;
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

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public byte[] getImage() {
            return image;
        }

        public void setImage(byte[] image) {
            this.image = image;
        }

        public byte[] getBanner() {
            return banner;
        }

        public void setBanner(byte[] banner) {
            this.banner = banner;
        }

        public Adresse getAdresse() {
            return adresse;
        }

        public void setAdresse(Adresse adresse) {
            this.adresse = adresse;
        }

        public Set<BoutiqueCategorie> getBoutiqueCategories() {
            return boutiqueCategories;
        }

        public void setBoutiqueCategories(Set<BoutiqueCategorie> boutiqueCategories) {
            this.boutiqueCategories = boutiqueCategories;
        }

        public Set<PersonnelBoutique> getPersonnelBoutiques() {
            return personnelBoutiques;
        }

        public void setPersonnelBoutiques(Set<PersonnelBoutique> personnelBoutiques) {
            this.personnelBoutiques = personnelBoutiques;
        }

        public Set<Follow> getFollowers() {
            return followers;
        }

        public void setFollowers(Set<Follow> followers) {
            this.followers = followers;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getBannerPath() {
            return bannerPath;
        }

        public void setBannerPath(String bannerPath) {
            this.bannerPath = bannerPath;
        }

        public Long getCategoryShopId() {
            return categoryShopId;
        }

        public void setCategoryShopId(Long categoryShopId) {
            this.categoryShopId = categoryShopId;
        }

        public BoutiqueDTO getDTO() {
            BoutiqueDTO boutiqueDTO = new BoutiqueDTO();
            boutiqueDTO.setId(this.id);
            boutiqueDTO.setNom(this.nom);
            boutiqueDTO.setTelephone(this.telephone);
            boutiqueDTO.setEmail(this.email);
            boutiqueDTO.setImage(this.getImage());
            boutiqueDTO.setBanner(this.getBanner());
            boutiqueDTO.setAdresseId(this.adresse.getId());
            boutiqueDTO.setCategorieIds(this.boutiqueCategories.stream()
                    .map(BoutiqueCategorie::getId)
                    .collect(Collectors.toList()));
            boutiqueDTO.setViews(this.views);
            boutiqueDTO.setImagePath(this.imagePath);
            boutiqueDTO.setBannerPath(this.bannerPath);
            boutiqueDTO.setCategoryShopId(this.categoryShopId);


            return boutiqueDTO;
        }
    }
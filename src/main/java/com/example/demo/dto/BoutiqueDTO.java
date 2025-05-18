// BoutiqueDTO.java
        package com.example.demo.dto;

        import lombok.Data;
        import lombok.Getter;
        import lombok.Setter;
        import org.springframework.web.multipart.MultipartFile;

        import java.util.List;

        @Data
        @Getter
        @Setter
        public class BoutiqueDTO {
            private long id;
            private String nom;
            private String telephone;
            private String email;
            private byte[] image;
            private byte[] banner;
            private Long adresseId;
            private List<Long> categorieIds;
            private int views;
            private List<Long> followerIds;
            private String imagePath;
            private String bannerPath;

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

            public Long getAdresseId() {
                return adresseId;
            }

            public void setAdresseId(Long adresseId) {
                this.adresseId = adresseId;
            }

            public List<Long> getCategorieIds() {
                return categorieIds;
            }

            public void setCategorieIds(List<Long> categorieIds) {
                this.categorieIds = categorieIds;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public List<Long> getFollowerIds() {
                return followerIds;
            }

            public void setFollowerIds(List<Long> followerIds) {
                this.followerIds = followerIds;
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
        }
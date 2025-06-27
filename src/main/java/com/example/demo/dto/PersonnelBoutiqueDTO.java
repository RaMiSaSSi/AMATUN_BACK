package com.example.demo.dto;

        public class PersonnelBoutiqueDTO extends UtilisateurInscritDTO {
            private String email;
            private String motDePasse;
            private String username;
            private long boutiqueId;
            // Getters and setters
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public long getBoutiqueId() {
                return boutiqueId;
            }

            public void setBoutiqueId(long boutiqueId) {
                this.boutiqueId = boutiqueId;
            }


        }
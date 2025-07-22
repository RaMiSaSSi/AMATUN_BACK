package com.example.demo.model;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import lombok.Data;

    @Data
    @Entity
    public class Notification {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long commandeId;
        private String message;
        private Long personnelBoutiqueId;

        public Notification(Long commandeId, String message, Long personnelBoutiqueId) {
            this.commandeId = commandeId;
            this.message = message;
            this.personnelBoutiqueId = personnelBoutiqueId;
        }

        public Notification() {}
    }
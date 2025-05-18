// File: src/main/java/com/example/demo/controller/PersonnelBoutique/PBProduitController.java
    package com.example.demo.controller.PersonnelBoutique;

    import com.example.demo.dto.ProduitDTO;
    import com.example.demo.service.PersonnelBoutique.PBProduitService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.time.LocalDate;
    import java.util.List;

    @RestController
    @RequestMapping("/personnel/boutique/produits")
    public class PBProduitController {

        @Autowired
        private PBProduitService pbProduitService;

        @GetMapping("/{id}")
        public ResponseEntity<ProduitDTO> getProduitById(@RequestParam Long personnelBoutiqueId, @PathVariable Long id) {
            ProduitDTO produitDTO = pbProduitService.getProduitById(id);
            return ResponseEntity.ok(produitDTO);
        }

        @GetMapping
        public ResponseEntity<List<ProduitDTO>> getAllProduitsForPersonnelBoutique(@RequestParam Long personnelBoutiqueId) {
            List<ProduitDTO> produits = pbProduitService.getAllProduitsForPersonnelBoutique(personnelBoutiqueId);
            return ResponseEntity.ok(produits);
        }


        @PostMapping(consumes = "multipart/form-data")
        public ResponseEntity<ProduitDTO> createProduitForPersonnelBoutique(
                @RequestParam Long personnelBoutiqueId,
                @RequestParam String nom,
                @RequestParam String description,
                @RequestParam double prix,
                @RequestParam int quantite,
                @RequestParam(required = false) Long categorieId,
                @RequestParam(required = false) Long sousCategorieId,
                @RequestParam("image") MultipartFile image) throws IOException {

            // Convert MultipartFile to byte array
            byte[] imageBytes = image.getBytes();

            // Create the product DTO
            ProduitDTO produitDTO = new ProduitDTO();
            produitDTO.setNom(nom);
            produitDTO.setDescription(description);
            produitDTO.setPrix(prix);
            produitDTO.setQuantite(quantite);
            produitDTO.setCategorieId(categorieId);
            produitDTO.setSousCategorieId(sousCategorieId);
            produitDTO.setImage(imageBytes);

            ProduitDTO createdProduit = pbProduitService.createProduitForPersonnelBoutique(personnelBoutiqueId, produitDTO);
            return ResponseEntity.ok(createdProduit);
        }

        @PutMapping("/{produitId}")
        public ResponseEntity<ProduitDTO> updateProduitForPersonnelBoutique(
                @RequestParam Long personnelBoutiqueId,
                @PathVariable Long produitId,
                @RequestParam String nom,
                @RequestParam String description,
                @RequestParam double prix,
                @RequestParam int quantite,
                @RequestParam (required = false) Boolean promo,
                @RequestParam (required = false) Double promotionPercentage,
                @RequestParam (required = false) Integer duree,
                @RequestParam (required = false) LocalDate startDate,
                @RequestParam(required = false) Long categorieId,
                @RequestParam(required = false) Long sousCategorieId,
                @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

            // Retrieve the existing product
            ProduitDTO existingProduit = pbProduitService.getProduitById(produitId);

            // Convert MultipartFile to byte array if provided, otherwise use the existing image
            byte[] imageBytes = existingProduit.getImage();
            if (image != null) {
                imageBytes = image.getBytes();
            }

            // Create the product DTO
            ProduitDTO produitDTO = new ProduitDTO();
            produitDTO.setNom(nom);
            produitDTO.setDescription(description);
            produitDTO.setPrix(prix);
            produitDTO.setQuantite(quantite);
            produitDTO.setPromo(promo != null ? promo : existingProduit.isPromo());
            produitDTO.setPromotionPercentage(promotionPercentage != null ? promotionPercentage : existingProduit.getPromotionPercentage());
            produitDTO.setDuree(duree != null ? duree : existingProduit.getDuree());
            produitDTO.setStartDate(startDate != null ? startDate : existingProduit.getStartDate());
            produitDTO.setCategorieId(categorieId);
            produitDTO.setSousCategorieId(sousCategorieId);
            produitDTO.setImage(imageBytes);

            ProduitDTO updatedProduit = pbProduitService.updateProduitForPersonnelBoutique(personnelBoutiqueId, produitId, produitDTO);
            return ResponseEntity.ok(updatedProduit);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProduit(@RequestParam Long personnelBoutiqueId, @PathVariable Long id) {
            pbProduitService.deleteProduit(id);
            return ResponseEntity.noContent().build();
        }
    }
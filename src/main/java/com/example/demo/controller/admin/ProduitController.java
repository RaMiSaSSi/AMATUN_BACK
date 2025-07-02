// File: src/main/java/com/example/demo/controller/admin/ProduitController.java
                    package com.example.demo.controller.admin;

                    import com.example.demo.dto.ProduitDTO;
                    import com.example.demo.service.Admin.ProduitService;
                    import org.springframework.beans.factory.annotation.Autowired;
                    import org.springframework.http.ResponseEntity;
                    import org.springframework.web.bind.annotation.*;
                    import org.springframework.web.multipart.MultipartFile;

                    import java.io.IOException;
                    import java.nio.file.Files;
                    import java.nio.file.Path;
                    import java.nio.file.Paths;
                    import java.time.LocalDate;
                    import java.util.List;

                    @RestController
                    @RequestMapping("/admin/produits")
                    public class ProduitController {

                        @Autowired
                        private ProduitService produitService;

                        @GetMapping("/{id}")
                        public ResponseEntity<ProduitDTO> getProduitById(@PathVariable Long id) {
                            ProduitDTO produitDTO = produitService.getProduitById(id);
                            return ResponseEntity.ok(produitDTO);
                        }

                        @GetMapping
                        public ResponseEntity<List<ProduitDTO>> getAllProduits() {
                            List<ProduitDTO> produits = produitService.getAllProduits();
                            return ResponseEntity.ok(produits);
                        }

                        @PostMapping(consumes = "multipart/form-data")
                        public ResponseEntity<ProduitDTO> createProduit(
                                @RequestParam String nom,
                                @RequestParam String description,
                                @RequestParam double prix,
                                @RequestParam int quantite,
                                @RequestParam(required = false) Boolean promo,
                                @RequestParam(required = false) Double promotionPercentage,
                                @RequestParam(required = false) Integer duree,
                                @RequestParam(required = false) LocalDate startDate,
                                @RequestParam Long boutiqueId,
                                @RequestParam(required = false) Long categorieId,
                                @RequestParam(required = false) Long sousCategorieId,
                                @RequestParam String marque, // Add marque parameter
                                @RequestParam("image") MultipartFile image) throws IOException {

                            byte[] imageBytes = image.getBytes();

                            ProduitDTO produitDTO = new ProduitDTO();
                            produitDTO.setNom(nom);
                            produitDTO.setDescription(description);
                            produitDTO.setPrix(prix);
                            produitDTO.setQuantite(quantite);
                            produitDTO.setPromo(promo != null ? promo : false);
                            produitDTO.setPromotionPercentage(promotionPercentage != null ? promotionPercentage : 0.0);
                            produitDTO.setDuree(duree != null ? duree : 0);
                            produitDTO.setStartDate(startDate);
                            produitDTO.setBoutiqueId(boutiqueId);
                            produitDTO.setCategorieId(categorieId);
                            produitDTO.setSousCategorieId(sousCategorieId);
                            produitDTO.setMarque(marque); // Set marque
                            if (image != null) {
                                String imagePath = saveFile(image, "uploads/produits");
                                produitDTO.setImagePath(imagePath);
                            }

                            ProduitDTO createdProduit = produitService.createProduit(produitDTO);
                            return ResponseEntity.ok(createdProduit);
                        }

                        @PutMapping(value = "/{produitId}", consumes = "multipart/form-data")
                        public ResponseEntity<ProduitDTO> updateProduit(
                                @PathVariable Long produitId,
                                @RequestParam String nom,
                                @RequestParam String description,
                                @RequestParam double prix,
                                @RequestParam int quantite,
                                @RequestParam(required = false) boolean promo,
                                @RequestParam(required = false) double promotionPercentage,
                                @RequestParam(required = false) int duree,
                                @RequestParam(required = false) LocalDate startDate,
                                @RequestParam Long boutiqueId,
                                @RequestParam(required = false) Long categorieId,
                                @RequestParam(required = false) Long sousCategorieId,
                                @RequestParam String marque, // Add marque parameter
                                @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

                            ProduitDTO existingProduit = produitService.getProduitById(produitId);

                            ProduitDTO produitDTO = new ProduitDTO();
                            produitDTO.setNom(nom);
                            produitDTO.setDescription(description);
                            produitDTO.setPrix(prix);
                            produitDTO.setQuantite(quantite);
                            produitDTO.setPromo(promo);
                            produitDTO.setPromotionPercentage(promotionPercentage);
                            produitDTO.setDuree(duree);
                            produitDTO.setStartDate(startDate);
                            produitDTO.setBoutiqueId(boutiqueId);
                            produitDTO.setCategorieId(categorieId);
                            produitDTO.setSousCategorieId(sousCategorieId);
                            produitDTO.setMarque(marque); // Set marque
                            produitDTO.setDateDeCreation(existingProduit.getDateDeCreation()); // Preserve original creation date
                            produitDTO.setViews(existingProduit.getViews()); // Preserve original views count

                            if (image != null) {
                                String imagePath = saveFile(image, "uploads/produits");
                                produitDTO.setImagePath(imagePath);
                            }

                            ProduitDTO updatedProduit = produitService.updateProduit(produitId, produitDTO);
                            return ResponseEntity.ok(updatedProduit);
                        }
                        private String saveFile(MultipartFile file, String directory) throws IOException {
                            Path uploadDir = Paths.get(directory);
                            if (!Files.exists(uploadDir)) {
                                Files.createDirectories(uploadDir);
                            }
                            String filePath = uploadDir.resolve(file.getOriginalFilename()).toString();
                            Files.write(Paths.get(filePath), file.getBytes());
                            return filePath;
                        }
                        @DeleteMapping("/{id}")
                        public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
                            produitService.deleteProduit(id);
                            return ResponseEntity.noContent().build();
                        }


                    }
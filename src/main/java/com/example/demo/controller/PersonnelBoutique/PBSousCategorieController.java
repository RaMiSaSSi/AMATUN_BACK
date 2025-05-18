// File: src/main/java/com/example/demo/controller/PersonnelBoutique/PBSousCategorieController.java
        package com.example.demo.controller.PersonnelBoutique;

        import com.example.demo.dto.SousCategorieDTO;
        import com.example.demo.model.SousCategorie;
        import com.example.demo.service.PersonnelBoutique.PBSousCategorieService;
        import lombok.RequiredArgsConstructor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

        @RestController
        @RequestMapping("/personnel/boutique")
        @RequiredArgsConstructor
        public class PBSousCategorieController {

            @Autowired
            private PBSousCategorieService pbSousCategorieService;

            @GetMapping("/sous-categorie")
            public ResponseEntity<SousCategorieDTO> getSousCategorieById(@RequestParam Long personnelId,
                                                                         @RequestParam Long sousCategorieId) {
                SousCategorieDTO sousCategorieDTO = pbSousCategorieService.getSousCategorieById(personnelId, sousCategorieId);
                return ResponseEntity.ok(sousCategorieDTO);
            }

            @GetMapping("/sous-categories")
            public ResponseEntity<List<SousCategorieDTO>> getAllSousCategories(@RequestParam Long personnelId) {
                List<SousCategorieDTO> sousCategories = pbSousCategorieService.getAllSousCategories(personnelId);
                return ResponseEntity.ok(sousCategories);
            }

            @GetMapping("/sous-categories/by-categorie")
            public ResponseEntity<List<SousCategorieDTO>> getSousCategoriesByCategorie(@RequestParam Long personnelId,
                                                                                       @RequestParam Long categorieId) {
                List<SousCategorieDTO> sousCategories = pbSousCategorieService.getSousCategoriesByCategorie(personnelId, categorieId);
                return ResponseEntity.ok(sousCategories);
            }

            @PostMapping("/sous-categorie")
            public ResponseEntity<SousCategorieDTO> createSousCategorie(@RequestParam Long personnelId,
                                                                        @RequestParam Long categorieId,
                                                                        @RequestBody SousCategorie sousCategorie) {
                SousCategorieDTO createdSousCategorie = pbSousCategorieService.createSousCategorie(personnelId, categorieId, sousCategorie);
                return ResponseEntity.ok(createdSousCategorie);
            }

            @PutMapping("/sous-categorie")
            public ResponseEntity<SousCategorieDTO> updateSousCategorie(@RequestParam Long personnelId,
                                                                        @RequestParam Long sousCategorieId,
                                                                        @RequestBody SousCategorie sousCategorie) {
                SousCategorieDTO updatedSousCategorie = pbSousCategorieService.updateSousCategorie(personnelId, sousCategorieId, sousCategorie);
                return ResponseEntity.ok(updatedSousCategorie);
            }

            @DeleteMapping("/sous-categorie")
            public ResponseEntity<Void> deleteSousCategorie(@RequestParam Long personnelId,
                                                            @RequestParam Long sousCategorieId) {
                pbSousCategorieService.deleteSousCategorie(personnelId, sousCategorieId);
                return ResponseEntity.noContent().build();
            }
        }
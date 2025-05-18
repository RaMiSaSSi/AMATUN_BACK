package com.example.demo.controller.PersonnelBoutique;

    import com.example.demo.dto.CategorieDTO;
    import com.example.demo.service.PersonnelBoutique.PBCategorieService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/personnel/boutique")
    @RequiredArgsConstructor
    public class PBCategorieController {
        @Autowired
        private PBCategorieService pbCategorieService;

        @GetMapping("/categorie")
        public ResponseEntity<CategorieDTO> getCategorieById(@RequestParam Long personnelId, @RequestParam Long categorieId) {
            CategorieDTO categorie = pbCategorieService.getCategorieById(personnelId, categorieId);
            return ResponseEntity.ok(categorie);
        }

        @GetMapping("/categories")
        public ResponseEntity<List<CategorieDTO>> getAllCategories(@RequestParam Long personnelId) {
            List<CategorieDTO> categories = pbCategorieService.getAllCategories(personnelId);
            return ResponseEntity.ok(categories);
        }

        // File: src/main/java/com/example/demo/controller/PersonnelBoutique/PBCategorieController.java
        @PostMapping("/categorie")
        public ResponseEntity<Void> createCategorie(@RequestParam Long personnelId, @RequestParam Long categorieId) {
            pbCategorieService.createCategorie(personnelId, categorieId);
            return ResponseEntity.ok().build();
        }

        @PutMapping("/categorie")
        public ResponseEntity<CategorieDTO> updateCategorie(@RequestParam Long personnelId, @RequestParam Long categorieId, @RequestBody CategorieDTO categorieDTO) {
            CategorieDTO updatedCategorie = pbCategorieService.updateCategorie(personnelId, categorieId, categorieDTO);
            return ResponseEntity.ok(updatedCategorie);
        }

        @DeleteMapping("/categorie")
        public ResponseEntity<Void> deleteCategorie(@RequestParam Long personnelId, @RequestParam Long categorieId) {
            pbCategorieService.deleteCategorie(personnelId, categorieId);
            return ResponseEntity.noContent().build();
        }
    }
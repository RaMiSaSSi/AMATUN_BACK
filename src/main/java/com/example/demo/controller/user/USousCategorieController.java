// File: src/main/java/com/example/demo/controller/User/USousCategorieController.java
package com.example.demo.controller.user;

import com.example.demo.dto.SousCategorieDTO;
import com.example.demo.service.User.USousCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/souscategories")
public class USousCategorieController {

    @Autowired
    private USousCategorieService uSousCategorieService;

    @GetMapping
    public ResponseEntity<List<SousCategorieDTO>> getAllSousCategories() {
        List<SousCategorieDTO> sousCategories = uSousCategorieService.getAllSousCategories();
        return ResponseEntity.ok(sousCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SousCategorieDTO> getSousCategorieById(@PathVariable Long id) {
        SousCategorieDTO sousCategorie = uSousCategorieService.getSousCategorieById(id);
        return ResponseEntity.ok(sousCategorie);
    }

    @GetMapping("/categorie/{categorieId}")
    public ResponseEntity<List<SousCategorieDTO>> getSousCategoriesByCategorieId(@PathVariable Long categorieId) {
        List<SousCategorieDTO> sousCategories = uSousCategorieService.getSousCategoriesByCategorieId(categorieId);
        return ResponseEntity.ok(sousCategories);
    }
}
// File: src/main/java/com/example/demo/controller/User/UCategorieController.java
package com.example.demo.controller.user;

import com.example.demo.dto.CategorieDTO;
import com.example.demo.service.User.UCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/categories")
public class UCategorieController {

    @Autowired
    private UCategorieService uCategorieService;

    @GetMapping
    public ResponseEntity<List<CategorieDTO>> getAllCategories() {
        List<CategorieDTO> categories = uCategorieService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieDTO> getCategorieById(@PathVariable Long id) {
        CategorieDTO categorie = uCategorieService.getCategorieById(id);
        return ResponseEntity.ok(categorie);
    }

    @GetMapping("/boutique/{boutiqueId}")
    public ResponseEntity<List<CategorieDTO>> getCategoriesByBoutiqueId(@PathVariable Long boutiqueId) {
        List<CategorieDTO> categories = uCategorieService.getCategoriesByBoutiqueId(boutiqueId);
        return ResponseEntity.ok(categories);
    }
}
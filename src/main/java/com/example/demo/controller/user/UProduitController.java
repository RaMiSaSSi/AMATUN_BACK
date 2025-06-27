// File: src/main/java/com/example/demo/controller/UProduitController.java
package com.example.demo.controller.user;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.service.User.UProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/user/produits")
public class UProduitController {

    @Autowired
    private UProduitService produitService;


    @GetMapping
    public Page<ProduitDTO> getAllProduits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return produitService.getAllProduits(pageable);
    }

    @GetMapping("/{id}")
    public ProduitDTO getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id);
    }

    @GetMapping("/categorie/{categorieId}")
    public Page<ProduitDTO> getProduitsByCategorieId(
            @PathVariable Long categorieId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return produitService.getProduitsByCategorieId(categorieId, pageable);
    }

    @GetMapping("/sous-categorie/{sousCategorieId}")
    public Page<ProduitDTO> getProduitsBySousCategorieId(
            @PathVariable Long sousCategorieId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return produitService.getProduitsBySousCategorieId(sousCategorieId, pageable);
    }

    @GetMapping("/boutique/{boutiqueId}")
    public Page<ProduitDTO> getProduitsByBoutiqueId(
            @PathVariable Long boutiqueId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return produitService.getProduitsByBoutiqueId(boutiqueId, pageable);
    }
    @GetMapping("/best-sellers")
    public ResponseEntity<List<ProduitDTO>> getBestSellingProducts(@RequestParam int limit) {
        List<ProduitDTO> bestSellingProducts = produitService.getBestSellingProducts(limit);
        return ResponseEntity.ok(bestSellingProducts);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProduitDTO>> searchProduits(@RequestParam String keyword) {
        List<ProduitDTO> produits = produitService.searchProduits(keyword);
        return ResponseEntity.ok(produits);
    }
    @GetMapping("/popular")
    public ResponseEntity<List<ProduitDTO>> getPopularProducts(@RequestParam int limit) {
        List<ProduitDTO> popularProducts = produitService.getPopularProducts(limit);
        return ResponseEntity.ok(popularProducts);
    }
    @GetMapping("/produits/count/{boutiqueId}")
    public ResponseEntity<Integer> countProduitsByBoutiqueId(@PathVariable Long boutiqueId) {
        int count = produitService.countProduitsByBoutiqueId(boutiqueId);
        return ResponseEntity.ok(count);
    }
}
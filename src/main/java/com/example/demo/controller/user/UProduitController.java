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
    @GetMapping("/marques")
    public ResponseEntity<List<String>> getAllMarques() {
        List<String> marques = produitService.getAllMarques();
        return ResponseEntity.ok(marques);
    }
    @GetMapping("/category-shop/{categoryShopId}")
    public Page<ProduitDTO> getProduitsByCategoryShopId(
            @PathVariable Long categoryShopId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return produitService.getProduitsByCategoryShopId(categoryShopId, pageable);
    }
    @GetMapping("/category-shop/{categoryShopId}/marques")
    public ResponseEntity<List<String>> getMarquesByCategoryShopId(@PathVariable Long categoryShopId) {
        List<String> marques = produitService.getMarquesByCategoryShopId(categoryShopId);
        return ResponseEntity.ok(marques);
    }
    @GetMapping("/category-shop/{categoryShopId}/search")
    public Page<ProduitDTO> searchProduitsByCategoryShopId(
            @PathVariable Long categoryShopId,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return produitService.searchProduitsByCategoryShopId(categoryShopId, keyword, pageable);
    }
    @GetMapping("/boutique/{boutiqueId}/search")
    public ResponseEntity<List<ProduitDTO>> searchProduitsInBoutique(
            @PathVariable Long boutiqueId,
            @RequestParam String keyword) {
        List<ProduitDTO> produits = produitService.searchProduitsInBoutique(boutiqueId, keyword);
        return ResponseEntity.ok(produits);
    }
    @GetMapping("/new")
    public ResponseEntity<Page<ProduitDTO>> getNewProducts(Pageable pageable) {
        Page<ProduitDTO> newProducts = produitService.getNewProducts(pageable);
        return ResponseEntity.ok(newProducts);
    }
    @GetMapping("/trending")
    public ResponseEntity<Page<ProduitDTO>> getTrendingProducts(Pageable pageable) {
        Page<ProduitDTO> trendingProducts = produitService.getTrendingProducts(pageable);
        return ResponseEntity.ok(trendingProducts);
    }
    @GetMapping("/promotions")
    public ResponseEntity<Page<ProduitDTO>> getPromotionalProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProduitDTO> produits = produitService.getPromotionalProducts(pageable);
        return ResponseEntity.ok(produits);
    }

}
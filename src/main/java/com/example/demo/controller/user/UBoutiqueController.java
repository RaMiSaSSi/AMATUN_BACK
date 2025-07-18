// File: src/main/java/com/example/demo/controller/User/UBoutiqueController.java
package com.example.demo.controller.user;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.dto.AdresseDTO;
import com.example.demo.dto.BoutiqueDTO;
import com.example.demo.service.User.UBoutiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/boutiques")
public class UBoutiqueController {

    @Autowired
    private UBoutiqueService uBoutiqueService;

    @GetMapping
    public ResponseEntity<List<BoutiqueDTO>> getAllBoutiques() {
        List<BoutiqueDTO> boutiques = uBoutiqueService.getAllBoutiques();
        return ResponseEntity.ok(boutiques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoutiqueDTO> getBoutiqueById(@PathVariable Long id) {
        BoutiqueDTO boutique = uBoutiqueService.getBoutiqueById(id);
        return ResponseEntity.ok(boutique);
    }
    @GetMapping("/{id}/adresse")
    public ResponseEntity<AdresseDTO> getBoutiqueAdresseById(@PathVariable Long id) {
        AdresseDTO adresse = uBoutiqueService.getBoutiqueAdresseById(id);
        return ResponseEntity.ok(adresse);
    }
    @PostMapping("/{boutiqueId}/follow")
    public ResponseEntity<Void> followBoutique(@PathVariable Long boutiqueId, @RequestParam Long utilisateurId) {
        uBoutiqueService.followBoutique(utilisateurId, boutiqueId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{boutiqueId}/unfollow")
    public ResponseEntity<Void> unfollowBoutique(@PathVariable Long boutiqueId, @RequestParam Long utilisateurId) {
        uBoutiqueService.unfollowBoutique(utilisateurId, boutiqueId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<BoutiqueDTO>> searchBoutiques(@RequestParam String keyword) {
        List<BoutiqueDTO> boutiques = uBoutiqueService.searchBoutiques(keyword);
        return ResponseEntity.ok(boutiques);
    }
    @GetMapping("/{id}/followers/count")
    public ResponseEntity<Integer> getFollowersCount(@PathVariable Long id) {
        int count = uBoutiqueService.getFollowersCount(id);
        return ResponseEntity.ok(count);
    }
    @GetMapping("/category-shop/{categoryShopId}")
    public ResponseEntity<List<BoutiqueDTO>> getBoutiquesByCategoryShopId(@PathVariable Long categoryShopId) {
        List<BoutiqueDTO> boutiques = uBoutiqueService.getBoutiquesByCategoryShopId(categoryShopId);
        return ResponseEntity.ok(boutiques);
    }
    @GetMapping("/{categoryShopId}/boutique-count")
    public ResponseEntity<Integer> getBoutiqueCountByCategoryShopId(@PathVariable Long categoryShopId) {
        int count = uBoutiqueService.getBoutiqueCountByCategoryShopId(categoryShopId);
        return ResponseEntity.ok(count);
    }
    @GetMapping("/most-followed")
    public ResponseEntity<Page<BoutiqueDTO>> getBoutiquesWithMostFollowers(Pageable pageable) {
        Page<BoutiqueDTO> mostFollowedBoutiques = uBoutiqueService.getBoutiquesWithMostFollowers(pageable);
        return ResponseEntity.ok(mostFollowedBoutiques);
    }
    // Java
    @GetMapping("/{boutiqueId}/products/count")
    public ResponseEntity<Integer> getProductCountByBoutiqueId(@PathVariable Long boutiqueId) {
        int count = uBoutiqueService.getProductCountByBoutiqueId(boutiqueId);
        return ResponseEntity.ok(count);
    }
    @GetMapping("/{boutiqueId}/is-followed")
    public ResponseEntity<Boolean> isBoutiqueFollowed(@PathVariable Long boutiqueId, @RequestParam Long utilisateurId) {
        boolean isFollowed = uBoutiqueService.isBoutiqueFollowed(utilisateurId, boutiqueId);
        return ResponseEntity.ok(isFollowed);
    }
    @GetMapping("/with-promotions")
    public ResponseEntity<List<BoutiqueDTO>> getBoutiquesWithPromotionalProducts() {
        List<BoutiqueDTO> boutiques = uBoutiqueService.getBoutiquesWithPromotionalProducts();
        return ResponseEntity.ok(boutiques);
    }
}
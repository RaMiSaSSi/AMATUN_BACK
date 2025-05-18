// File: src/main/java/com/example/demo/controller/User/UBoutiqueController.java
package com.example.demo.controller.user;

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

}
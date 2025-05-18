package com.example.demo.controller.PersonnelBoutique;

import com.example.demo.dto.AdresseDTO;
import com.example.demo.dto.BoutiqueDTO;
import com.example.demo.service.PersonnelBoutique.PBService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personnel/boutique")
@RequiredArgsConstructor
public class PersonnelBoutiqueController {
    @Autowired
    private PBService pbService;

    @GetMapping("/me")
    public ResponseEntity<BoutiqueDTO> getMyBoutique(@RequestParam Long personnelId) {
        BoutiqueDTO boutique = pbService.getBoutiqueForAuthenticatedPersonnel(personnelId);
        return ResponseEntity.ok(boutique);
    }
    @PutMapping("/me")
    public ResponseEntity<BoutiqueDTO> updateMyBoutique(@RequestParam Long personnelId, @RequestBody BoutiqueDTO boutiqueDTO) {
        BoutiqueDTO updatedBoutique = pbService.updateBoutiqueForPersonnel(personnelId, boutiqueDTO);
        return ResponseEntity.ok(updatedBoutique);
    }
    @PutMapping("/adresse")
    public ResponseEntity<AdresseDTO> updateMyBoutiqueAdresse(@RequestParam Long personnelId, @RequestBody AdresseDTO adresseDTO) {
        AdresseDTO updatedAdresse = pbService.updateBoutiqueAdresseForPersonnel(personnelId, adresseDTO);
        return ResponseEntity.ok(updatedAdresse);
    }
    @GetMapping("/adresse")
    public ResponseEntity<AdresseDTO> getAdresse(@RequestParam Long personnelId) {
        AdresseDTO adresse = pbService.getAdresse(personnelId);
        return ResponseEntity.ok(adresse);
    }
    @GetMapping("/views")
    public ResponseEntity<Integer> getBoutiqueViews(@RequestParam Long personnelId) {
        int views = pbService.getBoutiqueViews(personnelId);
        return ResponseEntity.ok(views);
    }
}
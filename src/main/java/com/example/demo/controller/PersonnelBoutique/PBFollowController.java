// File: src/main/java/com/example/demo/controller/admin/PersonnelBoutiqueAdminController.java
package com.example.demo.controller.PersonnelBoutique;

import com.example.demo.dto.UtilisateurInscritDTO;
import com.example.demo.service.PersonnelBoutique.PBFollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnelboutique")
public class PBFollowController {

    @Autowired
    private PBFollowersService pbFollowersService;

    @GetMapping("/{personnelBoutiqueId}/followers")
    public ResponseEntity<List<UtilisateurInscritDTO>> getFollowers(@PathVariable Long personnelBoutiqueId) {
        List<UtilisateurInscritDTO> followers = pbFollowersService.getFollowersByPersonnelBoutique(personnelBoutiqueId);
        return ResponseEntity.ok(followers);
    }
}
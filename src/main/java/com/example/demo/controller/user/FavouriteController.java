// File: src/main/java/com/example/demo/controller/user/FavouriteController.java
package com.example.demo.controller.user;

import com.example.demo.dto.FavouriteDTO;
import com.example.demo.service.User.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping("/{utilisateurId}/{produitId}")
    public ResponseEntity<Void> addFavourite(@PathVariable Long utilisateurId, @PathVariable Long produitId) {
        favouriteService.addFavourite(utilisateurId, produitId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{utilisateurId}/{produitId}")
    public ResponseEntity<Void> removeFavourite(@PathVariable Long utilisateurId, @PathVariable Long produitId) {
        favouriteService.removeFavourite(utilisateurId, produitId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{utilisateurId}")
    public ResponseEntity<List<FavouriteDTO>> getFavourites(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(favouriteService.getFavourites(utilisateurId));
    }

    @GetMapping("/{utilisateurId}/{produitId}/exists")
    public ResponseEntity<Boolean> userLikeProduit(@PathVariable Long utilisateurId, @PathVariable Long produitId) {
        return ResponseEntity.ok(favouriteService.userLikeProduit(utilisateurId, produitId));
    }
}
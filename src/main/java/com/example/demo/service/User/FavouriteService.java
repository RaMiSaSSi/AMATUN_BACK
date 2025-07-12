// File: src/main/java/com/example/demo/service/User/FavouriteService.java
package com.example.demo.service.User;

import com.example.demo.dto.FavouriteDTO;

import java.util.List;

public interface FavouriteService {
    void addFavourite(Long utilisateurId, Long produitId);
    void removeFavourite(Long utilisateurId, Long produitId);
    List<FavouriteDTO> getFavourites(Long utilisateurId);
    boolean userLikeProduit(Long utilisateurId, Long produitId);
}
// File: src/main/java/com/example/demo/service/User/UBoutiqueService.java
package com.example.demo.service.User;

import com.example.demo.dto.AdresseDTO;
import com.example.demo.dto.BoutiqueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UBoutiqueService {
    Page<BoutiqueDTO> getAllBoutiques(Pageable pageable);
    BoutiqueDTO getBoutiqueById(Long id);
    AdresseDTO getBoutiqueAdresseById(Long id);
    void followBoutique(Long utilisateurId, Long boutiqueId); // Add this method
    void unfollowBoutique(Long utilisateurId, Long boutiqueId); // New method
    List<BoutiqueDTO> searchBoutiques(String keyword); // New method
    int getFollowersCount(Long boutiqueId);
    List<BoutiqueDTO> getBoutiquesByCategoryShopId(Long categoryShopId); // New method
    int getBoutiqueCountByCategoryShopId(Long categoryShopId);
    Page<BoutiqueDTO> getBoutiquesWithMostFollowers(Pageable pageable);
    int getProductCountByBoutiqueId(Long boutiqueId);
    boolean isBoutiqueFollowed(Long utilisateurId, Long boutiqueId);
    List<BoutiqueDTO> getBoutiquesWithPromotionalProducts();
    List<BoutiqueDTO> getBoutiquesByAutoMotoCategory();
}
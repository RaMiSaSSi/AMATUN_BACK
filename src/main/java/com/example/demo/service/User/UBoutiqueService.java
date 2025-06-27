// File: src/main/java/com/example/demo/service/User/UBoutiqueService.java
package com.example.demo.service.User;

import com.example.demo.dto.AdresseDTO;
import com.example.demo.dto.BoutiqueDTO;

import java.util.List;

public interface UBoutiqueService {
    List<BoutiqueDTO> getAllBoutiques();
    BoutiqueDTO getBoutiqueById(Long id);
    AdresseDTO getBoutiqueAdresseById(Long id);
    void followBoutique(Long utilisateurId, Long boutiqueId); // Add this method
    void unfollowBoutique(Long utilisateurId, Long boutiqueId); // New method
    List<BoutiqueDTO> searchBoutiques(String keyword); // New method
    int getFollowersCount(Long boutiqueId);
}
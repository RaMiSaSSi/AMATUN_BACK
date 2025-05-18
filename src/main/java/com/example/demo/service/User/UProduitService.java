// File: src/main/java/com/example/demo/service/User/UProduitService.java
package com.example.demo.service.User;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UProduitService {
    Page<ProduitDTO> getAllProduits(Pageable pageable);    ProduitDTO getProduitById(Long id);
    List<ProduitDTO> getBestSellingProducts(int limit); // New method
    List<ProduitDTO> searchProduits(String keyword); // New method
Page<ProduitDTO> getProduitsByCategorieId(Long categorieId, Pageable pageable);
Page<ProduitDTO> getProduitsBySousCategorieId(Long sousCategorieId, Pageable pageable);
Page<ProduitDTO> getProduitsByBoutiqueId(Long boutiqueId, Pageable pageable);
    List<ProduitDTO> getPopularProducts(int limit);
}
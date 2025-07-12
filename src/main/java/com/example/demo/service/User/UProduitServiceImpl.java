// File: src/main/java/com/example/demo/service/User/UProduitServiceImpl.java
package com.example.demo.service.User;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.model.Produit;
import com.example.demo.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UProduitServiceImpl implements UProduitService {

    @Autowired
    private ProduitRepository produitRepository;


   @Override
   public Page<ProduitDTO> getAllProduits(Pageable pageable) {
       Page<Produit> produits = produitRepository.findAll(pageable);
       return produits.map(Produit::toDTO);
   }

    @Override
    public ProduitDTO getProduitById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit not found with id: " + id));

        // Increment the number of views
        produit.setViews(produit.getViews() + 1);
        produitRepository.save(produit); // Save the updated product

        return produit.toDTO();
    }
   @Override
   public Page<ProduitDTO> getProduitsByCategorieId(Long categorieId, Pageable pageable) {
       Page<Produit> produits = produitRepository.findByCategorieId(categorieId, pageable);
       return produits.map(Produit::toDTO);
   }

   @Override
   public Page<ProduitDTO> getProduitsBySousCategorieId(Long sousCategorieId, Pageable pageable) {
       Page<Produit> produits = produitRepository.findBySousCategorieId(sousCategorieId, pageable);
       return produits.map(Produit::toDTO);
   }

   @Override
   public Page<ProduitDTO> getProduitsByBoutiqueId(Long boutiqueId, Pageable pageable) {
       Page<Produit> produits = produitRepository.findByBoutiqueId(boutiqueId, pageable);
       return produits.map(Produit::toDTO);
   }
    @Override
    public List<ProduitDTO> getBestSellingProducts(int limit) {
        List<Produit> produits = produitRepository.findBestSellingProducts(limit);
        return produits.stream().map(Produit::toDTO).collect(Collectors.toList());
    }
    @Override
    public List<ProduitDTO> getPopularProducts(int limit) {
        List<Produit> produits = produitRepository.findPopularProducts(limit);
        return produits.stream().map(Produit::toDTO).collect(Collectors.toList());
    }
    @Override
    public List<ProduitDTO> searchProduits(String keyword) {
        List<Produit> produits = produitRepository.searchByName(keyword);
        return produits.stream().map(Produit::toDTO).collect(Collectors.toList());
    }
    @Override
    public int countProduitsByBoutiqueId(Long boutiqueId) {
        return produitRepository.countByBoutiqueId(boutiqueId);
    }
    @Override
    public List<String> getAllMarques() {
        return produitRepository.findAllMarques();
    }
    @Override
    public Page<ProduitDTO> getProduitsByCategoryShopId(Long categoryShopId, Pageable pageable) {
        Page<Produit> produits = produitRepository.findByCategoryShopId(categoryShopId, pageable);
        return produits.map(Produit::toDTO);
    }
    @Override
    public List<String> getMarquesByCategoryShopId(Long categoryShopId) {
        return produitRepository.findMarquesByCategoryShopId(categoryShopId);
    }
    @Override
    public Page<ProduitDTO> searchProduitsByCategoryShopId(Long categoryShopId, String keyword, Pageable pageable) {
        Page<Produit> produits = produitRepository.searchByCategoryShopId(categoryShopId, keyword, pageable);
        return produits.map(Produit::toDTO);
    }
    @Override
    public List<ProduitDTO> searchProduitsInBoutique(Long boutiqueId, String keyword) {
        List<Produit> produits = produitRepository.searchByBoutiqueIdAndName(boutiqueId, keyword);
        return produits.stream().map(Produit::toDTO).collect(Collectors.toList());
    }
    // Java
    @Override
    public Page<ProduitDTO> getNewProducts(Pageable pageable) {
        LocalDate fourDaysAgo = LocalDate.now().minusDays(4);
        Page<Produit> produits = produitRepository.findNewProducts(fourDaysAgo, pageable);
        return produits.map(Produit::toDTO);
    }
    @Override
    public Page<ProduitDTO> getTrendingProducts(Pageable pageable) {
        Page<Produit> produits = produitRepository.findTrendingProducts(pageable);
        return produits.map(Produit::toDTO);
    }
    @Override
    public Page<ProduitDTO> getPromotionalProducts(Pageable pageable) {
        Page<Produit> produits = produitRepository.findPromotionalProducts(pageable);
        return produits.map(Produit::toDTO);
    }
}
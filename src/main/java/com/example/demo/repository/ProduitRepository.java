// File: src/main/java/com/example/demo/repository/ProduitRepository.java
package com.example.demo.repository;

import com.example.demo.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategorieId(Long categorieId);
    List<Produit> findBySousCategorieId(Long sousCategorieId);
    List<Produit> findByBoutiqueId(Long boutiqueId);

    @Query("SELECT p FROM Produit p JOIN p.commandeProduits cp GROUP BY p ORDER BY SUM(cp.quantite) DESC")
    List<Produit> findBestSellingProducts(@Param("limit") int limit);
    @Query("SELECT p FROM Produit p WHERE LOWER(p.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Produit> searchByName(@Param("keyword") String keyword);
    Page<Produit> findAll(Pageable pageable);
    Page<Produit> findByCategorieId(Long categorieId, Pageable pageable);
    Page<Produit> findBySousCategorieId(Long sousCategorieId, Pageable pageable);
    Page<Produit> findByBoutiqueId(Long boutiqueId, Pageable pageable);
    @Query("SELECT p FROM Produit p ORDER BY p.quantite DESC") // Example: Popular products based on quantity
    List<Produit> findPopularProducts(@Param("limit") int limit);
}
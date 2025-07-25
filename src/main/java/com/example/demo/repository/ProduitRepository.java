// File: src/main/java/com/example/demo/repository/ProduitRepository.java
package com.example.demo.repository;

import com.example.demo.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategorieId(Long categorieId);
    List<Produit> findBySousCategorieId(Long sousCategorieId);
    List<Produit> findByBoutiqueId(Long boutiqueId);

    @Query("SELECT p, SUM(cp.quantite) as totalQuantity " +
            "FROM Produit p JOIN p.commandeProduits cp " +
            "GROUP BY p.id " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findBestSellingProducts(@Param("limit") int limit);
    @Query("SELECT p FROM Produit p WHERE LOWER(p.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Produit> searchByName(@Param("keyword") String keyword);
    Page<Produit> findAll(Pageable pageable);
    Page<Produit> findByCategorieId(Long categorieId, Pageable pageable);
    Page<Produit> findBySousCategorieId(Long sousCategorieId, Pageable pageable);
    Page<Produit> findByBoutiqueId(Long boutiqueId, Pageable pageable);
    @Query("SELECT p FROM Produit p ORDER BY p.views DESC")
    List<Produit> findPopularProducts(@Param("limit") int limit);
    int countByBoutiqueId(Long boutiqueId);
    @Query("SELECT DISTINCT p.marque FROM Produit p WHERE p.marque IS NOT NULL")
    List<String> findAllMarques();
    @Query("SELECT p FROM Produit p WHERE p.boutique.categoryShopId = :categoryShopId")
    Page<Produit> findByCategoryShopId(@Param("categoryShopId") Long categoryShopId, Pageable pageable);
    @Query("SELECT DISTINCT p.marque FROM Produit p WHERE p.boutique.categoryShopId = :categoryShopId AND p.marque IS NOT NULL")
    List<String> findMarquesByCategoryShopId(@Param("categoryShopId") Long categoryShopId);
    @Query("SELECT p FROM Produit p WHERE p.boutique.categoryShopId = :categoryShopId AND LOWER(p.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Produit> searchByCategoryShopId(@Param("categoryShopId") Long categoryShopId, @Param("keyword") String keyword, Pageable pageable);
    @Query("SELECT p FROM Produit p WHERE p.boutique.id = :boutiqueId AND LOWER(p.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Produit> searchByBoutiqueIdAndName(@Param("boutiqueId") Long boutiqueId, @Param("keyword") String keyword);
    @Query("SELECT p FROM Produit p WHERE p.dateDeCreation >= :startDate")
    Page<Produit> findNewProducts(LocalDate startDate, Pageable pageable);
    @Query("SELECT p FROM Produit p ORDER BY p.views DESC")
    Page<Produit> findTrendingProducts(Pageable pageable);
    // Java
    @Query(value = "SELECT * FROM produit p WHERE p.promo = true AND CURRENT_DATE BETWEEN p.start_date AND (p.start_date + p.duree * INTERVAL '1 DAY')", nativeQuery = true)
    Page<Produit> findPromotionalProducts(Pageable pageable);
}
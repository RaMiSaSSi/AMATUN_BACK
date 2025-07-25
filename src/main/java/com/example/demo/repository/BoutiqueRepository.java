package com.example.demo.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.Boutique;
import com.example.demo.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {

    @Query("SELECT b FROM Boutique b WHERE (:nom IS NULL OR b.nom = :nom) AND (:ville IS NULL OR b.adresse.ville = :ville)")
    List<Boutique> findByCriteria(@Param("nom") String nom, @Param("ville") String ville);

    @Query("SELECT b FROM Boutique b WHERE LOWER(b.nom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Boutique> searchByName(@Param("keyword") String keyword);

    @Query("SELECT b FROM Boutique b WHERE b.categoryShopId = :categoryShopId")
    List<Boutique> findByCategoryShopId(@Param("categoryShopId") Long categoryShopId);
    int countByCategoryShopId(Long categoryShopId);
    //
    @Query("SELECT b FROM Boutique b ORDER BY SIZE(b.followers) DESC")
    Page<Boutique> findBoutiquesWithMostFollowers(Pageable pageable);
    //
    @Query("SELECT COUNT(p) FROM Produit p WHERE p.boutique.id = :boutiqueId")
    int countProductsByBoutiqueId(@Param("boutiqueId") Long boutiqueId);

    @Query("SELECT DISTINCT p.boutique FROM Produit p WHERE p.promo = true")
    List<Boutique> findBoutiquesWithPromotionalProducts();

    @Query("SELECT b FROM Boutique b JOIN CategoryShop cs ON b.categoryShopId = cs.id WHERE cs.name = :categoryName")
    List<Boutique> findByCategoryShopName(@Param("categoryName") String categoryName);

    @Query("SELECT b FROM Boutique b ORDER BY b.id")
    Page<Boutique> findAllByOrderById(Pageable pageable);
}
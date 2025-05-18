// src/main/java/com/example/demo/repository/BoutiqueCategorieRepository.java
package com.example.demo.repository;

import com.example.demo.model.Boutique;
import com.example.demo.model.BoutiqueCategorie;
import com.example.demo.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoutiqueCategorieRepository extends JpaRepository<BoutiqueCategorie, Long> {
    List<BoutiqueCategorie> findByBoutiqueId(long boutiqueId);
    Optional<BoutiqueCategorie> findByCategorieIdAndBoutiqueId(Long categorieId, Long boutiqueId);
    Optional<BoutiqueCategorie> findByCategorie(Categorie categorie);
    Optional<BoutiqueCategorie> findByCategorieId(Long categorieId);
    Optional<BoutiqueCategorie> findByBoutiqueAndCategorie(Boutique boutique, Categorie categorie);

}
// src/main/java/com/example/demo/repository/SousCategorieRepository.java
package com.example.demo.repository;

import com.example.demo.model.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SousCategorieRepository extends JpaRepository<SousCategorie, Long> {
    List<SousCategorie> findByCategorieId(long categorieId);
    List<SousCategorie> findAllByCategorie_BoutiqueCategories_Boutique_Id(Long boutiqueId);

}
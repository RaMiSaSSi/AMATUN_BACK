// src/main/java/com/example/demo/repository/CategorieRepository.java
package com.example.demo.repository;

import com.example.demo.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findByBoutiqueCategories_Boutique_Id(long boutiqueId);
}
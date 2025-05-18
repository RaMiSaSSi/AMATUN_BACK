package com.example.demo.repository;

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
}
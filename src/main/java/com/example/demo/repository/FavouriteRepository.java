// File: src/main/java/com/example/demo/repository/FavouriteRepository.java
package com.example.demo.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    List<Favourite> findByUtilisateurId(Long utilisateurId);
    boolean existsByUtilisateurIdAndProduitId(Long utilisateurId, Long produitId);
    @Modifying
    @Transactional
    void deleteByUtilisateurIdAndProduitId(Long utilisateurId, Long produitId);
}
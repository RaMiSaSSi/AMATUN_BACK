// FollowRepository.java
package com.example.demo.repository;

import com.example.demo.model.Boutique;
import com.example.demo.model.Follow;
import com.example.demo.model.UtilisateurInscrit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUtilisateurId(Long utilisateurId);
    boolean existsByUtilisateurAndBoutique(UtilisateurInscrit utilisateur, Boutique boutique);
    Optional<Follow> findByUtilisateurAndBoutique(UtilisateurInscrit utilisateur, Boutique boutique);
    List<Follow> findByBoutiqueId(Long boutiqueId);
    int countByBoutique(Boutique boutique);

}
package com.example.demo.repository;

import com.example.demo.model.UtilisateurInscrit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurInscritRepository extends JpaRepository<UtilisateurInscrit, Long> {
    Optional<UtilisateurInscrit> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM UtilisateurInscrit u WHERE u.email LIKE %:critere% OR u.nom LIKE %:critere% OR u.telephone LIKE %:critere%")
    List<UtilisateurInscrit> findByCritere(@Param("critere") String critere);
}
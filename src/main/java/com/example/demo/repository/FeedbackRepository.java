package com.example.demo.repository;

import com.example.demo.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByBoutiqueId(Long boutiqueId);
    boolean existsByBoutiqueIdAndUtilisateurId(Long boutiqueId, Long utilisateurId);
}
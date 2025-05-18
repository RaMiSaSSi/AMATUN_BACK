package com.example.demo.repository;

    import com.example.demo.model.Commande;
    import com.example.demo.model.Statut;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface CommandeRepository extends JpaRepository<Commande, Long> {

        @Query("SELECT c FROM Commande c JOIN c.produits p WHERE p.produit.boutique.id = :boutiqueId")
        List<Commande> findByBoutiqueId(Long boutiqueId);
        @Query("SELECT c FROM Commande c WHERE c.client.id = :userId AND c.statut = :status")
        List<Commande> findByClientIdAndStatut(@Param("userId") Long userId, @Param("status") Statut status);
    }
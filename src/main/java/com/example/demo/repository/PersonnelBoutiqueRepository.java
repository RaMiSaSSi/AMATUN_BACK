// com/example/demo/repository/PersonnelBoutiqueRepository.java
package com.example.demo.repository;

import com.example.demo.model.PersonnelBoutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonnelBoutiqueRepository extends JpaRepository<PersonnelBoutique, Long> {
    PersonnelBoutique findByEmail(String email);
    Optional<PersonnelBoutique> findByTelephone(String telephone);
    @Query("SELECT pb FROM PersonnelBoutique pb " +
            "JOIN FETCH pb.boutique b " +
            "JOIN FETCH b.boutiqueCategories " +
            "WHERE pb.id = :personnelId")
    PersonnelBoutique findByIdWithBoutiqueCategories(@Param("personnelId") Long personnelId);
}
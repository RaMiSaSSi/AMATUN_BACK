// repository/AdresseRepository.java
package com.example.demo.repository;

import com.example.demo.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}
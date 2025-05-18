// File: src/main/java/com/example/demo/service/PersonnelBoutique/PBProduitService.java
package com.example.demo.service.PersonnelBoutique;

import com.example.demo.dto.ProduitDTO;

import java.util.List;

public interface PBProduitService {
    ProduitDTO getProduitById(Long id);
    List<ProduitDTO> getAllProduitsForPersonnelBoutique(Long personnelBoutiqueId);
    ProduitDTO createProduitForPersonnelBoutique(Long personnelBoutiqueId, ProduitDTO produitDTO);
    ProduitDTO updateProduitForPersonnelBoutique(Long personnelBoutiqueId, Long produitId, ProduitDTO produitDTO);
    void deleteProduit(Long id);
}
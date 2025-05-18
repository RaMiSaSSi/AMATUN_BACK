package com.example.demo.service.Admin;

import com.example.demo.dto.ProduitDTO;

import java.util.List;

public interface ProduitService {
    ProduitDTO getProduitById(Long id);
    List<ProduitDTO> getAllProduits();
    ProduitDTO createProduit(ProduitDTO produitDTO);
    ProduitDTO updateProduit(Long produitId, ProduitDTO produitDTO);
    void deleteProduit(Long id);
}
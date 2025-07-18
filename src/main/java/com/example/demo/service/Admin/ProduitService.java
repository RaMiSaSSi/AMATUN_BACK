package com.example.demo.service.Admin;

import com.example.demo.dto.ProduitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProduitService {
    ProduitDTO getProduitById(Long id);
    Page<ProduitDTO> getAllProduits(Pageable pageable);    ProduitDTO createProduit(ProduitDTO produitDTO);
    ProduitDTO updateProduit(Long produitId, ProduitDTO produitDTO);
    void deleteProduit(Long id);
}
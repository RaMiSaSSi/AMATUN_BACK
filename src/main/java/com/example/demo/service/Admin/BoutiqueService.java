package com.example.demo.service.Admin;

import com.example.demo.dto.BoutiqueDTO;
import com.example.demo.dto.CategorieDTO;

import java.io.IOException;
import java.util.List;

public interface BoutiqueService {
    BoutiqueDTO createBoutique(BoutiqueDTO boutiqueDTO) throws IOException;
    BoutiqueDTO updateBoutique(long id, BoutiqueDTO boutiqueDTO) throws IOException;
    void deleteBoutique(long id);
    BoutiqueDTO getBoutiqueById(long id);
    List<BoutiqueDTO> getAllBoutiques();
    List<CategorieDTO> getCategoriesByBoutiqueId(long boutiqueId);
    void removeCategorieFromBoutique(long boutiqueId, long categorieId);
    void addCategorieToBoutique(long boutiqueId, long categorieId);
    List<CategorieDTO> getUnassociatedCategories(long boutiqueId);

}